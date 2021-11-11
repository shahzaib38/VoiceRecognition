package com.app.myapplication.repo

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.app.myapplication.mediaplayer.AudioRecorder
import com.app.myapplication.mediaplayer.AudioRecorderFactory
import com.app.myapplication.mediaplayer.SpeechListenerHelper
import com.app.myapplication.network.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor( private val speechListenerHelper: SpeechListenerHelper): BaseRepository() {




    val liveData  = speechListenerHelper.speechToTextLiveData


    @RequiresApi(Build.VERSION_CODES.O)
  suspend  fun speechToText(localfilePath : String , context :Context ){

        speechListenerHelper.processAudio(localfilePath,context)

    }




    fun createAudioPlayer():AudioRecorder{

        return AudioRecorderFactory.createAudioRecorder()
    }

//
//    suspend fun convertAudioToString(audioEncode64 :String):AudioResponse{
//
//        val audioPost =AudioPost()
//          val audio =Audio()
//        audio.content=audioEncode64
//        val config =Config()
//        config.enableAutomaticPunctuation=true
//        config.encoding="LINEAR16"
//       config.languageCode="en-US"
//        config.model="default"
//    //    config.audioCount =1
//        config.sampleRatehertz=16000
//        audioPost.audio =audio
//        audioPost.config =config
//
//   return  speechApi.postSpeechData(audioPost)
//
//    }



}