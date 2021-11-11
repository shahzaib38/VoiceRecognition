package com.app.myapplication.mediaplayer

object AudioRecorderFactory {


    fun createAudioRecorder():AudioRecorder{
        return  AudioRecorderImpl()

    }



}