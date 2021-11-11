package com.app.myapplication.mediaplayer

interface AudioRecorder : LifeCycleOwner  {



    fun initializeRecorder()
    fun onIntialized()

    fun startRecording()

    fun stopRecording()


}