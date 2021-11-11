package com.app.myapplication.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Alternative {
    @SerializedName("transcript")
    @Expose
    var transcript: String? = null

    @SerializedName("confidence")
    @Expose
    var confidence: Float? = null
}