package com.bubble.epoxytest.model

import android.content.Context
import com.bubble.epoxytest.model.data.Category
import com.bubble.epoxytest.model.data.TopPageData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class LocalDataSource(context: Context): VideoDataSource {

    companion object {
        private const val TOP_PAGE_JSON_FILE = "top_page.json"
    }

    private val assetManager = context.assets

    private val gson = Gson()

    override suspend fun getTopPage(): TopPageData? = suspendCoroutine {
        loadJsonFile(TOP_PAGE_JSON_FILE)?.let { jsonString ->
            it.resumeWith(Result.success(gson.fromJson(jsonString, TopPageData::class.java)))
        } ?: run {
            it.resume(null)
        }
    }

    private fun loadJsonFile(file: String): String? {
        return try {
            val inputStream: InputStream = assetManager.open(file)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }
}