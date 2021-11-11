package com.app.myapplication.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class AudioResponse {
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
}