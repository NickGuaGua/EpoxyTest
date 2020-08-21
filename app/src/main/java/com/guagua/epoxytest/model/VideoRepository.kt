package com.guagua.epoxytest.model


class VideoRepository (
    private val localDataSource: VideoDataSource,
    private val remoteDataSource: VideoDataSource
) {

    fun getCategoriesObservable() = remoteDataSource.getCategoriesObservable()

    fun getVideosObservable(id: String) = remoteDataSource.getVideosObservable(id)
}