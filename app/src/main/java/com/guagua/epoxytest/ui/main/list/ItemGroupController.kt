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
        fun onSeeMoreClickListener(groupId: String, groupTitle: String)
    }

    override fun buildModels(data: List<Carousel<ListItem>>?) {
        data?.forEach {
            itemGroup {
                id(it.id)
                title(it.title)
                seeMore { it.items.size > 2 }
                onSeeMoreClickListener { model, parentView, clickedView, position ->
                    callbacks.onSeeMoreClickListener(it.id, it.title)
                }
            }
            carousel {
                id("${it.id}_carousel")
                numViewsToShowOnScreen(2.4f)
                paddingDp(16)
//                padding(com.airbnb.epoxy.Carousel.Padding.dp(16, 8, 0, 8, 16))
                withModelsFrom(it.items) { _, item ->
                    ListItemModelViewModel_()
                        .id(item.id)
                        .listItem(item)
                        .onClickListener { model, _, _, _ ->
                            callbacks.onListItemClick(item)
                        }
                }
            }
        }
    }
}