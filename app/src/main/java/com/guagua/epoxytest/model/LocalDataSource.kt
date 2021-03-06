package com.guagua.epoxytest.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guagua.epoxytest.MyApplication
import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.model.data.Video
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class LocalDataSource: VideoDataSource {

    companion object {
        private const val TOP_PAGE_JSON_FILE = "top_page.json"
    }

    private val assetManager = MyApplication.appContext.assets

    private val gson = Gson()

    override suspend fun getCategories(): List<Category>? = suspendCoroutine {
        loadJsonFile(TOP_PAGE_JSON_FILE)?.let { jsonString ->
            val listType = object : TypeToken<ArrayList<Category>?>() {}.type
            it.resumeWith(Result.success(gson.fromJson(jsonString, listType)))
        } ?: run {
            it.resume(null)
        }
    }

    override fun getCategoriesObservable(): Single<Response<List<Category>>> {
        return Single.just(Response.success(listOf(Category(1, "", listOf()))))
    }

    override fun getVideosObservable(id: String): Single<Response<List<Video>>> {
        return Single.just(Response.success(listOf(Video(1, "", ""))))
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