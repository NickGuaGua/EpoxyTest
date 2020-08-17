package com.guagua.epoxytest.model

import com.guagua.epoxytest.di.VideoLocalDataSource
import com.guagua.epoxytest.di.VideoRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository (
    @VideoLocalDataSource private val localDataSource: VideoDataSource,
    @VideoRemoteDataSource private val remoteDataSource: VideoDataSource
) {

    suspend fun getCategories() = withContext(Dispatchers.IO) {
        remoteDataSource.getCategories()
//        localDataSource.getCategories()
    }
}