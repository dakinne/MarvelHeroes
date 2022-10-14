package com.noox.marvelheroes.comic.data.cache

import androidx.room.Entity

@Entity(tableName = "comic", primaryKeys = ["characterId", "imageUrl"])
data class ComicEntity(
    val characterId: Int,
    val imageUrl: String
)
