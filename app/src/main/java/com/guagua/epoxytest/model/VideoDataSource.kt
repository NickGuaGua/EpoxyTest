package com.guagua.epoxytest.model

import com.guagua.epoxytest.model.data.Category

interface VideoDataSource {
    suspend fun getCategories(): List<Category>?
}