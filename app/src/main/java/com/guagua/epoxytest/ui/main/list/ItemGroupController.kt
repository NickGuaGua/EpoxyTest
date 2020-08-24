package com.guagua.epoxytest.ui.main.list

import com.airbnb.epoxy.TypedEpoxyController
import com.guagua.epoxytest.ui.extension.carousel
import com.guagua.epoxytest.ui.extension.withModelsFrom
import com.guagua.epoxytest.view.data.Carousel
import com.guagua.epoxytest.view.data.ListItem

class ItemGroupController(
    private val callbacks: AdapterCallbacks
) : TypedEpoxyController<List<Carousel<ListItem>>>() {

    interface AdapterCallbacks {
        fun onListItemClick(item: ListItem)
    }

    override fun buildModels(data: List<Carousel<ListItem>>?) {
        data?.forEach {
            itemGroup {
                id(it.id)
                itemGroup(it)
            }
            carousel {
                id("${it.id}_carousel")
                paddingDp(16)
                withModelsFrom(it.items) { index, item ->
                    when {
                        index % 2 == 0 -> ListItemModel_()
                            .id(item.id)
                            .listItem(item)
                            .clickListener { model, _, _, _ ->
                                callbacks.onListItemClick(model.listItem())
                            }
                        else -> ListItemModelViewModel_()
                            .id(item.id)
                            .listItem(item)
                            .onClickListener { model, _, _, _ ->
                                callbacks.onListItemClick(model.listItem())
                            }
                    }
                }
            }
        }
    }
}