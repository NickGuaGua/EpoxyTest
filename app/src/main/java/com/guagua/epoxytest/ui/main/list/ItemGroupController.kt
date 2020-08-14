package com.guagua.epoxytest.ui.main.list

import com.airbnb.epoxy.TypedEpoxyController
import com.guagua.epoxytest.ui.extension.carousel
import com.guagua.epoxytest.ui.extension.withModelsFrom
import com.guagua.epoxytest.view.data.Carousel
import com.guagua.epoxytest.view.data.ListItem

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