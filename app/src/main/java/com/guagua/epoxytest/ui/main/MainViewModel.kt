package com.guagua.epoxytest.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guagua.epoxytest.InjectUtil
import com.guagua.epoxytest.model.data.TopPageData
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
