package com.guagua.epoxytest.ui.main

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guagua.epoxytest.model.VideoRepository
import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.ui.extension.getSuccessBody
import com.guagua.epoxytest.ui.main.list.ItemGroupController
import com.guagua.epoxytest.view.data.ListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import javax.inject.Inject

class MainViewModel @ViewModelInject @Inject constructor(
    application: Application,
    private val repository: VideoRepository
) : AndroidViewModel(application), ItemGroupController.AdapterCallbacks,
    CoroutineScope by MainScope() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    fun fetchCategories() {
        repository.getCategoriesObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.getSuccessBody() ?: return@subscribe

                it.getSuccessBody()?.forEach { category ->
                    repository.getVideosObservable(category.id.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ response ->
                            category.videos = response.getSuccessBody()

                            // Update each category.
                            _categories.postValue(it.body())
                        }, { throwable ->
                            Log.d("Nick", throwable.message)
                        })
                }

                // Show categories title first.
                _categories.postValue(it.body())
            }, {})
    }

    override fun onListItemClick(item: ListItem) {
        Log.d("Nick", "on item click: $item")
    }
}
