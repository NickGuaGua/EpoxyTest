package com.guagua.epoxytest.di

import com.guagua.epoxytest.ui.main.MainFragment
import com.guagua.epoxytest.ui.main.list.ItemGroupController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainFragment(): MainFragment = MainFragment()

    @Provides
    fun provideItemGroupController(): ItemGroupController = ItemGroupController()
}