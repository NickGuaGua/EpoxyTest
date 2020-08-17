package com.guagua.epoxytest.ui.extension

import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.model.data.Video
import com.guagua.epoxytest.view.data.Carousel
import com.guagua.epoxytest.view.data.ListItem


fun Category.toCarousel(): Carousel<ListItem> {
    return Carousel(id.toString(), title, videos.map { it.toListItem() })
}

fun Video.toListItem(): ListItem {
    return ListItem(id.toString(), title, photo)
}