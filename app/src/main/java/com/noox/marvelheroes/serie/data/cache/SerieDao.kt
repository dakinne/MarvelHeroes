package com.noox.marvelheroes.serie.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SerieDao {

    @Query("SELECT * FROM serie where characterId = :characterId")
    fun getSeriesOfCharacter(characterId: Int): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSeries(serie: List<SerieEntity>)
}
