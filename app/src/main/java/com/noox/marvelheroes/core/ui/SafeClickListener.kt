package com.noox.marvelheroes.core.ui

import android.os.SystemClock
import android.view.View

class SafeClickListener(
        private var interval: Int = 1000,
        private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > interval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeCLick(view)
        }
    }
}