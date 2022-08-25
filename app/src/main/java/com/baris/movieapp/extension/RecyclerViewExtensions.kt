package com.baris.movieapp.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/***
 * Created by @Barış Keser on 25.08.2022.
 */

fun RecyclerView.scrolledEnd(visibleItemEndListener: () -> Unit) =
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            val visibleChildCount = recyclerView.childCount
            val totalChildCount = this@scrolledEnd.layoutManager?.itemCount

            val positionView =
                (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if (!recyclerView.canScrollVertically(1) && dy > 0) {
                if (totalChildCount != 0 && (visibleChildCount + positionView) >= totalChildCount!!) {
                    visibleItemEndListener.invoke()
                }
            }
        }
    })