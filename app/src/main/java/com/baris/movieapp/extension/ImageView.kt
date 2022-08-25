package com.baris.movieapp.extension

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target

/***
 * Created by @Barış Keser on 25.08.2022.
 */

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?
) {
    val requestOptions = RequestOptions().apply {
        placeholderRes?.let { placeholder(it) }
        errorRes?.let { error(it) }
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadImage(url: String?, radius: Int, @DrawableRes errorPlaceholderRes: Int? = null) {

    val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(radius))

    errorPlaceholderRes?.let {
        requestOptions.error(it)
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadImage(
    url: String?,
    radius: Int,
    @DrawableRes errorPlaceholderRes: Int? = null,
    originalSize: Boolean
) {

    val requestOptions = RequestOptions().override(
        Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).dontTransform()

    errorPlaceholderRes?.let {
        requestOptions.error(it)
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadImage(uri: Uri?, radius: Int, @DrawableRes errorPlaceholderRes: Int? = null) {

    val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(radius))

    errorPlaceholderRes?.let {
        requestOptions.error(it)
    }

    Glide.with(this)
        .load(uri)
        .apply(requestOptions)
        .into(this)
}

/*
 * Load model into ImageView as a circle image with borderSize (optional) using Glide
 *
 * @param model - Any object supported by Glide (Uri, File, Bitmap, String, resource id as Int, ByteArray, and Drawable)
 * @param borderSize - The border size in pixel
 * @param borderColor - The border color
 */
fun <T> ImageView.loadCircularImage(
    model: T,
    borderSize: Float = 0F,
    borderColor: Int = Color.WHITE
) {
    Glide.with(context)
        .asBitmap()
        .load(model)
        .apply(RequestOptions.circleCropTransform())
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                setImageDrawable(
                    resource?.run {
                        RoundedBitmapDrawableFactory.create(
                            resources,
                            if (borderSize > 0) {
                                createBitmapWithBorder(borderSize, borderColor)
                            } else {
                                this
                            }
                        ).apply {
                            isCircular = true
                        }
                    }
                )
            }
        })
}