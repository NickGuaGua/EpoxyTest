package com.guagua.epoxytest.ui.main.list

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.guagua.epoxytest.R


@EpoxyModelClass(layout = R.layout.view_line_header)
abstract class LineHeaderModel : EpoxyModelWithHolder<LineHeaderModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var seeMore: () -> Boolean

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onSeeMoreClickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = title
        holder.seeMoreBtn.setOnClickListener(onSeeMoreClickListener)
    }

    class Holder : EpoxyHolder() {
        lateinit var title: TextView
        lateinit var seeMoreBtn: Button

        override fun bindView(itemView: View) {
            title = itemView.findViewById(R.id.title)
            seeMoreBtn = itemView.findViewById(R.id.seeMoreBtn)
        }
    }
}