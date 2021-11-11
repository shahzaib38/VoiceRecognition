package com.app.myapplication.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Config {
    @SerializedName("enableAutomaticPunctuation")
    var enableAutomaticPunctuation: Boolean? = null

    @SerializedName("encoding")
    var encoding: String? = null

    @SerializedName("languageCode")
    var languageCode: String? = null

    @SerializedName("model")
    var model: String? = null

    @SerializedName("audioChannelCount")
    var audioCount :Int =1

    @SerializedName("sampleRateHertz")
    var sampleRatehertz :Int  =16000


}