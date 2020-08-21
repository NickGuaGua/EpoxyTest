package com.guagua.epoxytest.model

import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.model.data.Video
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface VideoDataSource {
    suspend fun getCategories(): List<Category>?

    fun getCategoriesObservable(): Single<Response<List<Category>>>

    fun getVideosObservable(id: String): Single<Response<List<Video>>>
}