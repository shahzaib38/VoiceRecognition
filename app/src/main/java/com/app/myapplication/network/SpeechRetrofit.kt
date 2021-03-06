package com.app.myapplication.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SpeechRetrofit {


    const val BASE_URL = "https://speech.googleapis.com/"

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().writeTimeout(7, TimeUnit.SECONDS)
            .readTimeout(7, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS).build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getApiService() : SpeechApi{


        return getRetrofit().create(SpeechApi::class.java) }









}