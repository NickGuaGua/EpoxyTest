package com.bubble.epoxytest.ui.extension

import com.bubble.epoxytest.model.data.Category
import com.bubble.epoxytest.model.data.Series
import com.bubble.epoxytest.view.data.Carousel
import com.bubble.epoxytest.view.data.ListItem


fun Category.toCarousel(): Carousel<ListItem> {
    return Carousel(id.toString(), title, series.map { it.toListItem() })
}

fun Series.toListItem(): ListItem {
    return ListItem(id.toString(), title, photo)
}