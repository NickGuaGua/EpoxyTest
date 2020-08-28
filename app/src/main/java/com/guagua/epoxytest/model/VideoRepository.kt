package com.guagua.epoxytest.model

import com.guagua.epoxytest.di.VideoLocalDataSource
import com.guagua.epoxytest.di.VideoRemoteDataSource
import javax.inject.Inject


class VideoRepository @Inject constructor(
    @VideoLocalDataSource private val localDataSource: VideoDataSource,
    @VideoRemoteDataSource private val remoteDataSource: VideoDataSource
) {

    fun getCategoriesObservable() = remoteDataSource.getCategoriesObservable()

    fun getVideosObservable(id: String) = remoteDataSource.getVideosObservable(id)
}