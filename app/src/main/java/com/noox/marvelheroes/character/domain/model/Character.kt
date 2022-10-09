package com.noox.marvelheroes.character.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val comics: Int,
    val events: Int,
    val series: Int,
    val stories: Int

): Parcelable
