package com.noox.marvelheroes.core.ui

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(
    private val drawable: Drawable,
    private val paddingLeft: Int = 0,
): RecyclerView.ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val dividerLeft = parent.paddingLeft + paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0..childCount -2) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + drawable.intrinsicHeight
            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            drawable.draw(canvas)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }

        outRect.top = drawable.intrinsicHeight
    }
}
