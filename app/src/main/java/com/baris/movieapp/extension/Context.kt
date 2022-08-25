package com.baris.movieapp.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/***
 * Created by @Barış Keser on 25.08.2022.
 */

fun Context?.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Context.getBitmapFromVectorDrawable(@DrawableRes drawableId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, drawableId)
    drawable?.let {
        val bitmap = Bitmap.createBitmap(
            it.intrinsicWidth,
            it.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        it.apply {
            setBounds(0, 0, canvas.width, canvas.height)
            draw(canvas)
        }
        return bitmap
    } ?: return null
}