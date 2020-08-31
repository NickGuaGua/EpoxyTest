package com.guagua.epoxytest.ui.main.list

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.guagua.epoxytest.R
import com.guagua.epoxytest.view.ColorFilterTransformation
import com.guagua.epoxytest.view.data.ListItem
import kotlinx.android.synthetic.main.view_list_item.view.*


@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class ListItemModelView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.view_list_item, this, true)
    }

    @ModelProp
    fun setListItem(item: ListItem) {
        title.text = item.title
        Glide.with(context)
            .load(item.photo)
            .transform(
                CenterCrop(),
                ColorFilterTransformation(Color.parseColor("#88666666")),
                RoundedCorners(36)
            )
            .into(photo)
    }

    @ModelProp(ModelProp.Option.DoNotHash)
    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
    }
}