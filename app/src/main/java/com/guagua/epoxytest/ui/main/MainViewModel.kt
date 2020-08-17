package com.guagua.epoxytest.ui.main

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guagua.epoxytest.model.VideoRepository
import com.guagua.epoxytest.model.data.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: VideoRepository
) : AndroidViewModel(application), CoroutineScope by MainScope() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    fun fetchTopPage() = launch {
        repository.getCategories()?.let {
            _categories.postValue(it)
        }
    }

}
