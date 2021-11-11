package com.app.myapplication.mediaplayer;


import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.app.myapplication.R;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
  import com.google.cloud.speech.v1.RecognitionConfig;
  import com.google.cloud.speech.v1.RecognizeRequest;
  import com.google.cloud.speech.v1.RecognizeResponse;
  import com.google.cloud.speech.v1.SpeechClient;
  import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
  import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.protobuf.ByteString;

import org.apache.http.auth.Credentials;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
  import java.nio.file.Path;
 import java.nio.file.Paths;




public class EncodeUtils {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void sampleRecognize(String localFilePath ,Context context ) throws IOException {

        InputStream stream = context.getResources().openRawResource(R.raw.credential);
        SpeechSettings settings = SpeechSettings.newBuilder().setCredentialsProvider(
                new CredentialsProvider() {
                    @Override
                    public com.google.auth.Credentials getCredentials() throws IOException {
                        return GoogleCredentials.fromStream(stream);
                    }
                }


        ).build();

        try (SpeechClient speechClient = SpeechClient.create(settings)) {


            // The language of the supplied audio
            String languageCode = "en-US";

            // Sample rate in Hertz of the audio data sent
            int sampleRateHertz = 16000;

            // Encoding of audio data sent. This sample sets this explicitly.
            // This field is optional for FLAC and WAV audio formats.
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.LINEAR16;
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setLanguageCode(languageCode)
                            .setSampleRateHertz(sampleRateHertz)
                            .setEncoding(encoding)
                            .build();
            Path path = Paths.get(localFilePath);
            byte[] data = Files.readAllBytes(path);
            ByteString content = ByteString.copyFrom(data);
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(content).build();
            RecognizeRequest request =
                    RecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();
            RecognizeResponse response = speechClient.recognize(request);
            for (SpeechRecognitionResult result : response.getResultsList()) {
                // First alternative is the most probable result
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.println("Transcript: %s\n"+ alternative.getTranscript());
            }
        } catch (Exception exception) {
            System.out.println("Failed to create the client due to: " + exception);
        }
    }


    }


