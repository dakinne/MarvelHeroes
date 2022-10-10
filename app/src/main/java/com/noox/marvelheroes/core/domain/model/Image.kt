package com.noox.marvelheroes.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val url: String): Parcelable
