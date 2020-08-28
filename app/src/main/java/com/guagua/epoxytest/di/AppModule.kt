package com.guagua.epoxytest.di

import com.guagua.epoxytest.model.LocalDataSource
import com.guagua.epoxytest.model.RemoteDataSource
import com.guagua.epoxytest.model.VideoDataSource
import com.guagua.epoxytest.model.service.VideoService
import com.guagua.epoxytest.ui.scheduler.SchedulerProvider
import com.guagua.epoxytest.ui.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://5f38dd7841c94900169bffa2.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()
}