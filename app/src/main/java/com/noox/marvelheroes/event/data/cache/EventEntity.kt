package com.noox.marvelheroes.event.data.cache

import androidx.room.Entity

@Entity(tableName = "event", primaryKeys = ["characterId", "imageUrl"])
data class EventEntity(
    val characterId: Int,
    val imageUrl: String
)
