package com.bubble.epoxytest.ui.main.list

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bubble.epoxytest.R
import com.bubble.epoxytest.view.data.ListItem
import com.bumptech.glide.Glide


@EpoxyModelClass(layout = R.layout.view_line_item)
abstract class ListItemModel : EpoxyModelWithHolder<ListItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var lineItem: ListItem

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = lineItem.title
        Glide.with(holder.contentView.context).load(lineItem.photo).into(holder.photo)
    }

    override fun shouldSaveViewState(): Boolean = true

    class Holder : EpoxyHolder() {
        lateinit var contentView: View
        lateinit var title: TextView
        lateinit var photo: ImageView

        override fun bindView(itemView: View) {
            contentView = itemView
            title = itemView.findViewById(R.id.title)
            photo = itemView.findViewById(R.id.photo)
        }
    }
}