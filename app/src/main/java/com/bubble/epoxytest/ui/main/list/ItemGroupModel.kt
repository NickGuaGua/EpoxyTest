package com.bubble.epoxytest.ui.main.list

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bubble.epoxytest.R
import com.bubble.epoxytest.view.data.Carousel
import com.bubble.epoxytest.view.data.ListItem


@EpoxyModelClass(layout = R.layout.view_item_group)
abstract class ItemGroupModel : EpoxyModelWithHolder<ItemGroupModel.Holder>() {

    @EpoxyAttribute
    lateinit var itemGroup: Carousel<ListItem>

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = itemGroup.title
    }

    class Holder : EpoxyHolder() {
        lateinit var title: TextView

        override fun bindView(itemView: View) {
            title = itemView.findViewById(R.id.title)
        }
    }
}