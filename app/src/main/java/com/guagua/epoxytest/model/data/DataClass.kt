package com.guagua.epoxytest.model.data

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("series") var videos: List<Video>? = listOf()
)

data class Video(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("photo") val photo: String)