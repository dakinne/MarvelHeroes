package com.noox.marvelheroes.serie.data.cache

import androidx.room.Entity

@Entity(tableName = "serie", primaryKeys = ["characterId", "imageUrl"])
data class SerieEntity(
    val characterId: Int,
    val imageUrl: String
)
