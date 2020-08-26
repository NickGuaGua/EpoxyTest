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
import com.guagua.epoxytest.ui.scheduler.SchedulerProvider
import com.guagua.epoxytest.view.data.ListItem
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class MainViewModel @ViewModelInject @Inject constructor(
    application: Application,
    private val repository: VideoRepository,
    private val schedulerProvider: SchedulerProvider
) : AndroidViewModel(application), ItemGroupController.AdapterCallbacks {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val compositeDisposable = CompositeDisposable()

    fun fetchCategories() = async {
        repository.getCategoriesObservable()
            .subscribeOn(schedulerProvider.io())
            .flatMapObservable {
                val categories = it.getSuccessBody() ?: listOf()
                _categories.postValue(categories) // Update categories title first
                Observable.fromIterable(categories)
            }
            .flatMapSingle { category ->
                repository.getVideosObservable(category.id.toString())
                    .map {
                        category.apply {
                            videos = it.getSuccessBody()
                        }
                    }
            }
            .toList()
            .observeOn(schedulerProvider.main())
            .subscribe({
                _categories.postValue(it)
            }, {})
    }

    override fun onListItemClick(item: ListItem) {
        Log.d("Nick", "on item click: $item")
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun test() {
        _categories.value = listOf(Category(2, ""))
    }

    private fun async(disposable: () -> Disposable) {
        compositeDisposable.add(disposable.invoke())
    }
}
