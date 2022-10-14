package com.noox.marvelheroes.event.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {

    @Query("SELECT * FROM event where characterId = :characterId")
    fun getEventsOfCharacter(characterId: Int): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvents(event: List<EventEntity>)
}
