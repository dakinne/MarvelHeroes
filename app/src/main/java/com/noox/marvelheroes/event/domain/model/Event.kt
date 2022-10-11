package com.noox.marvelheroes.event.domain.model

import android.os.Parcelable
import com.noox.marvelheroes.core.domain.model.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(val image: Image): Parcelable
