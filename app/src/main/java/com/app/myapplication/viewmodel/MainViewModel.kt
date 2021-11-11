package com.app.myapplication.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.app.myapplication.navigator.MainNavigator
import com.app.myapplication.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : BaseViewModel<MainNavigator>(mainRepository) {

    val audioRecorder = mainRepository.createAudioPlayer()

    val liveData  = mainRepository.liveData





fun speak(){

    getNavigator().speak()

}




//    fun getUsers(name: String) = liveData(Dispatchers.IO) {
//        // emit(Resource.loading(data = null))
//
//        viewModelScope.launch(Dispatchers.IO) {
//
//            try {
//                val result = mainRepository.convertAudioToString(name).results
//
//                result
//                    ?.forEach {
//                        it.alternatives?.forEach {
//
//                            println("result is  "+it.transcript)
//                            emit(it.transcript)
//                        }
//
//                    }
//
//
//            } catch (exception: Exception) {
//                println("Error "+exception.message)
//                emit(exception.message ?: "Error Occurred!")
//            }
//
//        }
//    }


    fun startRecording(){
        viewModelScope.launch(Dispatchers.IO) {

            audioRecorder.startRecording()

        }
    }



    fun stopRecording(){
        viewModelScope.launch(Dispatchers.IO) {

            audioRecorder.stopRecording()

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
      fun speechToText(localfilePath : String , context :Context ){

        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.speechToText(localfilePath, context)


        }

    }


}