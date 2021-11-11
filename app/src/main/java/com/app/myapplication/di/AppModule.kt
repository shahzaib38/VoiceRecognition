package com.app.myapplication.di

import android.content.Context
import com.app.myapplication.R
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.speech.v1.SpeechSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {




    @Provides
    @Singleton
    fun getSpeechSettings(@ApplicationContext applicationContext: Context):SpeechSettings{

        val stream = applicationContext.resources.openRawResource(R.raw.credential)
        return  SpeechSettings.newBuilder().setCredentialsProvider {
            GoogleCredentials.fromStream(
                stream
            )
        }.build()

    }







}