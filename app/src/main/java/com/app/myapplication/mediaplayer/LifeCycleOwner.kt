package com.app.myapplication.mediaplayer

import androidx.lifecycle.LifecycleObserver

interface LifeCycleOwner :LifecycleObserver{

   fun  onPause()
   fun onStop()
    fun onResume()
   fun onStart()
    fun onCreate()
    fun  onDestroy()

}