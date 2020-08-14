package com.bubble.epoxytest.model

import com.bubble.epoxytest.model.data.TopPageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository (private val localDataSource: VideoDataSource) {

    suspend fun getTopPage() = withContext(Dispatchers.IO) {
        localDataSource.getTopPage()
    }
}