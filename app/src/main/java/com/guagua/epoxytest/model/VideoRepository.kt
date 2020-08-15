package com.guagua.epoxytest.model

import com.guagua.epoxytest.di.VideoLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository (
    @VideoLocalDataSource private val localDataSource: VideoDataSource
) {

    suspend fun getTopPage() = withContext(Dispatchers.IO) {
        localDataSource.getTopPage()
    }
}