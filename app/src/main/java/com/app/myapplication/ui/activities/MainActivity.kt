package com.app.myapplication.ui.activities

//import cafe.adriel.androidaudioconverter.callback.IConvertCallback
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.app.myapplication.BR
import com.app.myapplication.R
import com.app.myapplication.databinding.MainDataBinding
import com.app.myapplication.ext.requestCameraPermission
import com.app.myapplication.mediaplayer.AudioRecorderImpl
import com.app.myapplication.mediaplayer.RecordingHelper
import com.app.myapplication.mediaplayer.WavAudioRecorder
import com.app.myapplication.navigator.MainNavigator
import com.app.myapplication.permission.InternetConnection
import com.app.myapplication.permissions.AudioPermission.AUDIO_PERMISSION
import com.app.myapplication.permissions.AudioPermission.AUDIO_PERMISSION_ARRAY
import com.app.myapplication.permissions.AudioPermission.hasPermissions
import com.app.myapplication.ui.dialogs.InternetConnectionDialog
import com.app.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class MainActivity   : BaseActivity<MainViewModel, MainDataBinding>(),MainNavigator  ,View.OnTouchListener {

    private val mViewModel by viewModels<MainViewModel>()

    private var mMainDataBinding : MainDataBinding?=null
    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mViewModel



    val recordingHelper = RecordingHelper.getInstance()


    private   fun  changeMicState(state :Int =R.drawable.mic_icon){
        mMainDataBinding?.speakerId?.setImageResource(state) }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        mMainDataBinding = getViewDataBinding()

        mViewModel.setNavigator(this)

        mMainDataBinding?.run {
            speakerId.setOnTouchListener(this@MainActivity)
        }
        val isPermissionGranted =(hasPermissions(this, AUDIO_PERMISSION_ARRAY))
        if(isPermissionGranted){
            lifecycle.addObserver(mViewModel.audioRecorder)

        } else {
            requestCameraPermission()

        }


        mViewModel.liveData.observe(this , Observer {text ->

            if(text!=null){

                mMainDataBinding?.listeningId?.text =text


            }




        })

    }







    override fun onBackPressed() {
        super.onBackPressed()
    }


    private fun toast(name: String){
        Toast.makeText(this, name, Toast.LENGTH_LONG).show()
    }

    override fun speak() {

        toast("Listening")
    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AUDIO_PERMISSION) {
            val cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (cameraPermission)            lifecycle.addObserver(AudioRecorderImpl())
            else finish()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when(event?.action){

            MotionEvent.ACTION_DOWN -> {


                if(!InternetConnection.isInternetAvailable(this)) {
                    InternetConnectionDialog.getInstance().showDialog(supportFragmentManager)
                    return false }


                mMainDataBinding?.listeningId?.text = "Listening"

                changeMicState(R.drawable.mic_icon_pressed)
                recordingHelper.startRecording(object : RecordingHelper.RecordingListener {
                    override fun onRecordingSucceeded(output: File) {
                        mViewModel.speechToText(output.absolutePath ,this@MainActivity)
                    }

                    override fun onRecordingFailed(e: java.lang.Exception?) {
                        //  Toast.makeText(this@MainActivity, e?.message, Toast.LENGTH_LONG).show()


                        println("error in recording" + e?.message)
                    }


                })


                return true
            }

            MotionEvent.ACTION_UP -> {
                changeMicState(R.drawable.mic_icon)

                recordingHelper.stopRecording();


                return true
            }


        }

        return false


    }


}
