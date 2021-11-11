package com.app.myapplication.ext

import android.app.Activity
import androidx.fragment.app.Fragment
import com.app.myapplication.permissions.AudioPermission.AUDIO_PERMISSION
import com.app.myapplication.permissions.AudioPermission.AUDIO_PERMISSION_ARRAY

fun Activity.requestCameraPermission() {
    requestPermissions(AUDIO_PERMISSION_ARRAY , AUDIO_PERMISSION)
}
