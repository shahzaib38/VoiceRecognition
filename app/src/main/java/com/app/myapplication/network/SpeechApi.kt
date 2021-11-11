package com.app.myapplication.network

import retrofit2.http.Body
import retrofit2.http.POST

interface SpeechApi {



    @POST("v1p1beta1/speech:recognize?key=AIzaSyBkgNSEu5RwDfTLCr-96vZnvfDV58nP0YA")
    suspend  fun postSpeechData(@Body audioPost: AudioPost) :AudioResponse

}