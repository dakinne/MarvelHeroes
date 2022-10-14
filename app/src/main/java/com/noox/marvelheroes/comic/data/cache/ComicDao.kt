package com.noox.marvelheroes.comic.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ComicDao {

    @Query("SELECT * FROM comic where characterId = :characterId")
    fun getComicsOfCharacter(characterId: Int): List<ComicEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComics(comics: List<ComicEntity>)
}
