package com.guagua.epoxytest.ui.extension

import com.guagua.epoxytest.model.data.Category
import com.guagua.epoxytest.model.data.Series
import com.guagua.epoxytest.view.data.Carousel
import com.guagua.epoxytest.view.data.ListItem


fun Category.toCarousel(): Carousel<ListItem> {
    return Carousel(id.toString(), title, series.map { it.toListItem() })
}

fun Series.toListItem(): ListItem {
    return ListItem(id.toString(), title, photo)
}