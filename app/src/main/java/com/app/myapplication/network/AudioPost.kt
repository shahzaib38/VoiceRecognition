package com.app.myapplication.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class AudioPost {
    @SerializedName("audio")
    var audio: Audio? = null

    @SerializedName("config")
    var config: Config? = null
}