package com.bubble.epoxytest.ui.main.list

import com.airbnb.epoxy.TypedEpoxyController
import com.bubble.epoxytest.ui.extension.carousel
import com.bubble.epoxytest.ui.extension.withModelsFrom
import com.bubble.epoxytest.view.data.Carousel
import com.bubble.epoxytest.view.data.ListItem

class ItemGroupController : TypedEpoxyController<List<Carousel<ListItem>>>() {

    override fun buildModels(data: List<Carousel<ListItem>>?) {
        data?.forEach {
            itemGroup {
                id(it.id)
                itemGroup(it)
            }
            carousel {
                id("${it.id}_carousel")
                withModelsFrom(it.items) {
                    ListItemModel_()
                        .id(it.id)
                        .lineItem(it)
                }
            }
        }
    }
}