package com.guagua.epoxytest.ui.extension

import retrofit2.Response

fun <T> Response<T>.getSuccessBody(): T? {
    return if (isSuccessful) body() else null
}