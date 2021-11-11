package com.app.myapplication.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Result {
    @SerializedName("alternatives")
    @Expose
    var alternatives: List<Alternative>? = null

    @SerializedName("languageCode")
    @Expose
    var languageCode: String? = null
}