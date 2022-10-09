package com.noox.marvelheroes.core.extensions

import android.content.res.Resources
import android.util.TypedValue

fun Float.dpToPx(): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
).toInt()

fun Int.dpToPx(): Int = this.toFloat().dpToPx()
