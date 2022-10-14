package com.noox.marvelheroes.character.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val totalComics: Int,
    val totalEvents: Int,
    val totalSeries: Int
)
