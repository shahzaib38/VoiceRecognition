package com.app.myapplication.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat



object AudioPermission {

    const val  AUDIO_PERMISSION :Int  = 0XFACE


    val AUDIO_PERMISSION_ARRAY : Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)


    @RequiresApi(Build.VERSION_CODES.N)
    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {


        permissions.forEach { permission ->  //Lambda Trailing
            if(isPermissionGranted(context,permission)){
                return false } }
        return true }



    private fun isPermissionGranted(context: Context, permission :String) : Boolean{
        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED }




    fun isCameraPermissionGranted(context: Context):Boolean{
        return hasPermissions(context, AUDIO_PERMISSION_ARRAY) }



}