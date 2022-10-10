package com.noox.marvelheroes.comic.domain.model

import android.os.Parcelable
import com.noox.marvelheroes.core.domain.model.Image
import kotlinx.parcelize.Parcelize

@Parcelize
class Comic(val image: Image): Parcelable
