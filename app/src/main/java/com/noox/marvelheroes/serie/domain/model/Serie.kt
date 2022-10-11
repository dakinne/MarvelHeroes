package com.noox.marvelheroes.serie.domain.model

import android.os.Parcelable
import com.noox.marvelheroes.core.domain.model.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Serie(val image: Image): Parcelable
