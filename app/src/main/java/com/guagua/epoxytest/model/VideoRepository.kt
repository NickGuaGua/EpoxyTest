package com.guagua.epoxytest.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository (private val localDataSource: VideoDataSource) {

    suspend fun getTopPage() = withContext(Dispatchers.IO) {
        localDataSource.getTopPage()
    }
}