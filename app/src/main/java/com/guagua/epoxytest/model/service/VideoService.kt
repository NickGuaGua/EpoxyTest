package com.guagua.epoxytest.model.service

import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.model.data.Video
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface VideoService {

    @GET("/categories")
    fun getCategories(): Call<List<Category>>

    @GET("/categories/{categoryId}/videos")
    fun getVideos(@Path("categoryId") id: String): Call<List<Video>>

    @GET("/categories")
    fun getCategoriesSingle(): Single<Response<List<Category>>>

    @GET("/categories/{categoryId}/videos")
    fun getVideosSingle(@Path("categoryId") id: String): Single<Response<List<Video>>>
}