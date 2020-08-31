package com.guagua.epoxytest.di

import com.guagua.epoxytest.ui.main.MainFragment
import com.guagua.epoxytest.ui.main.list.HomePageController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object MainPageModule {

    @FragmentScoped
    @Provides
    fun provideItemGroupController(mainFragment: MainFragment): HomePageController =
        HomePageController(mainFragment)
}