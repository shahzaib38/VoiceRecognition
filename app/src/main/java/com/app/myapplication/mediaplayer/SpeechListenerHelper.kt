package com.app.myapplication.mediaplayer

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.myapplication.R
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.speech.v1.*
import com.google.protobuf.ByteString
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import javax.inject.Inject

class SpeechListenerHelper @Inject constructor() {

    val speechToTextLiveData = MutableLiveData<String>()

            fun getSpeechToTextLiveData(): LiveData<String> {
                return speechToTextLiveData }



   @RequiresApi(Build.VERSION_CODES.O)
    fun processAudio(localFilePath: String ,context  :Context){


       val stream: InputStream = context.resources.openRawResource(R.raw.credential)
       val settings = SpeechSettings.newBuilder().setCredentialsProvider {
           GoogleCredentials.fromStream(
               stream
           )
       }.build()

       try {
           SpeechClient.create(settings).use { speechClient ->


               // The language of the supplied audio
               val languageCode = "en-US"

               // Sample rate in Hertz of the audio data sent
               val sampleRateHertz = 16000

               // Encoding of audio data sent. This sample sets this explicitly.
               // This field is optional for FLAC and WAV audio formats.
               val encoding =
                   RecognitionConfig.AudioEncoding.LINEAR16
               val config =
                   RecognitionConfig.newBuilder()
                       .setLanguageCode(languageCode)
                       .setSampleRateHertz(sampleRateHertz)
                       .setEncoding(encoding)
                       .build()
               val path = Paths.get(localFilePath)
               val data = Files.readAllBytes(path)
               val content = ByteString.copyFrom(data)
               val audio =
                   RecognitionAudio.newBuilder().setContent(content).build()
               val request =
                   RecognizeRequest.newBuilder().setConfig(config)
                       .setAudio(audio).build()
               val response = speechClient.recognize(request)
               for (result in response.resultsList) {
                   // First alternative is the most probable result
                   val alternative =
                       result.alternativesList[0]
                   println(
                       """
                    Transcript: %s
                    ${alternative.transcript}
                    """.trimIndent()
                   )
                   speechToTextLiveData.postValue(alternative.transcript)
               }
           }
       } catch (exception: Exception) {
           speechToTextLiveData.postValue("Failed to create the client due to: $exception")
           println("Failed to create the client due to: $exception")
       }


//        try
//        {
//            SpeechClient.create(settings).use { speechClient ->
//
//                // The language of the supplied audio
//                val languageCode = "en-US"
//
//                // Sample rate in Hertz of the audio data sent
//                val sampleRateHertz = 16000
//
//                // Encoding of audio data sent. This sample sets this explicitly.
//                // This field is optional for FLAC and WAV audio formats.
//                val encoding =
//                    RecognitionConfig.AudioEncoding.LINEAR16
//                val config =
//                    RecognitionConfig.newBuilder()
//                        .setLanguageCode(languageCode)
//                        .setSampleRateHertz(sampleRateHertz)
//                        .setEncoding(encoding)
//                        .build()
//                val path: Path = Paths.get(localFilePath)
//                val data = Files.readAllBytes(path)
//                val content = ByteString.copyFrom(data)
//                val audio =
//                    RecognitionAudio.newBuilder().setContent(content).build()
//                val request =
//                    RecognizeRequest.newBuilder().setConfig(config)
//                        .setAudio(audio).build()
//                val response = speechClient.recognize(request)
//                for (result in response.resultsList) {
//                    // First alternative is the most probable result
//                    val alternative =
//                        result.alternativesList[0]
//
//
//                    speechToTextLiveData.postValue(
//                        """
//                    ${alternative.transcript}
//                    """.trimIndent()
//                    )
//
//                }
//            }
//            }
//        catch (exception: Exception) {
//            speechToTextLiveData.postValue("Failed to create the client due to: $exception")
//            println("Failed to create the client due to: $exception")
//        }


    }








}