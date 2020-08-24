package com.guagua.epoxytest.di

import com.guagua.epoxytest.ui.main.MainFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @ActivityScoped
    @Provides
    fun provideMainFragment(): MainFragment = MainFragment()
}