package com.app.myapplication.mediaplayer

import android.media.*
import android.media.AudioFormat.ENCODING_PCM_16BIT
import android.os.Environment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import java.io.File
import java.io.IOException

class AudioRecorderImpl  :AudioRecorder ,MediaRecorder.OnErrorListener , MediaRecorder.OnInfoListener ,AudioManager.AudioRecordingCallback(){

    var mediaRecorder : MediaRecorder?=null
    val   audioFile = File(Environment.getExternalStorageDirectory(), "audio_test5.3gp")

    var isRecording =false


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy(){
        println("OnDestroy")
        release() }



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume(){
        println("ON_RESUME") }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause(){
        println("ON_PAUSE")
        release()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        release()

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
   override fun onStart(){


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        initializeRecorder()
    }

    override fun initializeRecorder() {
         mediaRecorder = MediaRecorder()


        mediaRecorder?.let {mediaRecorder->
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(audioFile.absolutePath)
            mediaRecorder.setAudioSamplingRate(16000)
            mediaRecorder.setAudioChannels(1)
            mediaRecorder.setOnInfoListener(this)
            mediaRecorder.setOnErrorListener(this) }
       prepareRecorder()

    }



    fun prepareRecorder(){
        println("Prepared")

        try {
            mediaRecorder?.prepare()

        }catch (illegalException: IllegalStateException){

            println("Illgal Exception " + illegalException.message)
        }catch (ioException: IOException){

            println("ioException Exception " + ioException.message)

        }



    }

   override fun startRecording(){

       initializeRecorder()
            mediaRecorder?.start()

       isRecording =true


    }

  override  fun stopRecording(){

      if(isRecording) {


          mediaRecorder?.stop()

          isRecording =false
      }
  }

    private fun release() {

        var mMediaRecorder = mediaRecorder

        if (mMediaRecorder != null) {
          //  mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;


        }
    }
    override fun onIntialized() {

    }

    override fun onError(mr: MediaRecorder?, what: Int, extra: Int) {

        if(mr!=null){


        }

    }

    override fun onInfo(mr: MediaRecorder?, what: Int, extra: Int) {
        if(mr!=null){


        }
    }


}