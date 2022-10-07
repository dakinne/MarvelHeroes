package com.noox.marvelheroes.core.extensions

import android.view.View
import com.noox.marvelheroes.core.ui.SafeClickListener

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { onSafeClick(it) })
}
