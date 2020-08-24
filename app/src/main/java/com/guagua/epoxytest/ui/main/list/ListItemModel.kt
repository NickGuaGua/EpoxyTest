package com.guagua.epoxytest.ui.main.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.guagua.epoxytest.R
import com.guagua.epoxytest.view.data.ListItem


@EpoxyModelClass(layout = R.layout.view_list_item)
abstract class ListItemModel : EpoxyModelWithHolder<ListItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var listItem: ListItem

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = listItem.title
        Glide.with(holder.contentView.context).load(listItem.photo).into(holder.photo)
        holder.contentView.setOnClickListener(clickListener)
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