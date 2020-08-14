package com.guagua.epoxytest.model

import com.guagua.epoxytest.model.data.TopPageData

interface VideoDataSource {
    suspend fun getTopPage(): TopPageData?
}