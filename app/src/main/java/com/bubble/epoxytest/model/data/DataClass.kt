package com.bubble.epoxytest.model.data

import com.google.gson.annotations.SerializedName

data class TopPageData(@SerializedName("category") val categories: List<Category>)

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("series") val series: List<Series>)

data class Series(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("photo") val photo: String)