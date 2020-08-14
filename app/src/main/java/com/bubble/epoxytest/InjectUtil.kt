package com.bubble.epoxytest

import android.content.Context
import com.bubble.epoxytest.model.LocalDataSource
import com.bubble.epoxytest.model.VideoRepository

object InjectUtil {

    fun provideVideoRepository(context: Context): VideoRepository = VideoRepository(provideLocalVideoDataSource(context))

    private fun provideLocalVideoDataSource(context: Context): LocalDataSource = LocalDataSource(context)
}