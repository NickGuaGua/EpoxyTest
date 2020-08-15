package com.guagua.epoxytest.di

import android.content.Context
import com.guagua.epoxytest.model.LocalDataSource
import com.guagua.epoxytest.model.VideoDataSource
import com.guagua.epoxytest.model.VideoRepository
import com.guagua.epoxytest.ui.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.sql.DataSource

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainFragment(): MainFragment = MainFragment()

    @Provides
    @VideoLocalDataSource
    fun provideVideoLocalDataSource(): VideoDataSource {
        return LocalDataSource()
    }

    @Provides
    fun provideVideoRepository(@VideoLocalDataSource dataSource: VideoDataSource): VideoRepository {
        return VideoRepository(dataSource)
    }
}