package com.guagua.epoxytest.model

import com.google.gson.Gson
import com.guagua.epoxytest.MyApplication
import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.model.data.Video
import com.guagua.epoxytest.model.service.VideoService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class RemoteDataSource(private val service: VideoService): VideoDataSource {

    override suspend fun getCategories(): List<Category>? = suspendCoroutine {
        GlobalScope.launch {
            val categories = getCategory()
            categories?.forEach { category ->
                category.videos = getVideos(category.id.toString()) ?: emptyList()
            } ?: run {
                it.resume(null)
            }

            it.resumeWith(Result.success(categories))
        }
    }

    private suspend fun getCategory(): List<Category>? = suspendCoroutine {
        service.getCategories().enqueue(object : Callback<List<Category>?> {
            override fun onFailure(call: Call<List<Category>?>, t: Throwable) {
                it.resume(null)
            }

            override fun onResponse(
                call: Call<List<Category>?>,
                response: Response<List<Category>?>
            ) {
                it.resumeWith(Result.success(response.body()))
            }
        })
    }

    private suspend fun getVideos(id: String): List<Video>? = suspendCoroutine {
        service.getVideos(id).enqueue(object : Callback<List<Video>> {
            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                it.resume(null)
            }

            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                it.resumeWith(Result.success(response.body()))
            }
        })
    }
}