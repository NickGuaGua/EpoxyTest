package com.guagua.epoxytest.di

import com.guagua.epoxytest.model.LocalDataSource
import com.guagua.epoxytest.model.RemoteDataSource
import com.guagua.epoxytest.model.VideoDataSource
import com.guagua.epoxytest.model.VideoRepository
import com.guagua.epoxytest.model.service.VideoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @VideoLocalDataSource
    fun provideVideoLocalDataSource(): VideoDataSource {
        return LocalDataSource()
    }

    @Provides
    @VideoRemoteDataSource
    fun provideVideoRemoteDataSource(service: VideoService): VideoDataSource {
        return RemoteDataSource(service)
    }

    @Provides
    fun provideVideoService(retrofit: Retrofit): VideoService {
        return retrofit.create(VideoService::class.java)
    }

    @Provides
    fun provideVideoRepository(
        @VideoLocalDataSource localDataSource: VideoDataSource,
        @VideoRemoteDataSource remoteDataSource: VideoDataSource
    ): VideoRepository {
        return VideoRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://5f38dd7841c94900169bffa2.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}