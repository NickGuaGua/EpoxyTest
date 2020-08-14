package com.bubble.epoxytest.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bubble.epoxytest.InjectUtil
import com.bubble.epoxytest.model.LocalDataSource
import com.bubble.epoxytest.model.data.Category
import com.bubble.epoxytest.model.data.TopPageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application), CoroutineScope by MainScope() {

    private val _categories = MutableLiveData<TopPageData>()
    val categories: LiveData<TopPageData> = _categories

    private val repository = InjectUtil.provideVideoRepository(application.applicationContext)

    fun fetchTopPage() = launch {
        repository.getTopPage()?.let {
            _categories.postValue(it)
        }
    }

}
