package com.guagua.epoxytest.view

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.nio.charset.Charset
import java.security.MessageDigest


class ColorFilterTransformation(private val color: Int) : BitmapTransformation() {

    companion object {
        private const val ID = "com.guagua.epoxytest.view.ImageMaskTransformation"
        private val ID_BYTES: ByteArray = ID.toByteArray(Charset.forName("UTF-8"))
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES);
    }

    override fun transform(
        pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int
    ): Bitmap {
        val width = toTransform.width
        val height = toTransform.height

        val config =
            if (toTransform.config != null) toTransform.config else Bitmap.Config.ARGB_8888
        val bitmap = pool[width, height, config]

//        setCanvasBitmapDensity(toTransform, bitmap)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(toTransform, 0f, 0f, paint)

        return bitmap
    }

    override fun equals(other: Any?): Boolean {
        return other is ColorFilterTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }
}