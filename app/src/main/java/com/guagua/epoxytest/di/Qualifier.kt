package com.guagua.epoxytest.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VideoLocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VideoRemoteDataSource