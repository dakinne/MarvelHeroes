package com.noox.marvelheroes.core.extensions

import android.content.res.Resources
import android.util.TypedValue

fun Float.dpToPx(): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
)

fun Int.dpToPx(): Int = this.toFloat().dpToPx().toInt()
