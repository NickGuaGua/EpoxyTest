package com.guagua.epoxytest

import android.content.Context
import com.guagua.epoxytest.model.LocalDataSource
import com.guagua.epoxytest.model.VideoRepository

object InjectUtil {

    fun provideVideoRepository(context: Context): VideoRepository = VideoRepository(provideLocalVideoDataSource(context))

    private fun provideLocalVideoDataSource(context: Context): LocalDataSource = LocalDataSource(context)
}