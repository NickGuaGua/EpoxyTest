package com.bubble.epoxytest.model

import com.bubble.epoxytest.model.data.TopPageData

interface VideoDataSource {
    suspend fun getTopPage(): TopPageData?
}