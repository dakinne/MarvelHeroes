package com.noox.marvelheroes.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noox.marvelheroes.character.data.cache.CharacterDao
import com.noox.marvelheroes.character.data.cache.CharacterEntity
import com.noox.marvelheroes.comic.data.cache.ComicDao
import com.noox.marvelheroes.comic.data.cache.ComicEntity
import com.noox.marvelheroes.event.data.cache.EventDao
import com.noox.marvelheroes.event.data.cache.EventEntity
import com.noox.marvelheroes.serie.data.cache.SerieDao
import com.noox.marvelheroes.serie.data.cache.SerieEntity

@Database(
    entities = [
        CharacterEntity::class,
        ComicEntity::class,
        EventEntity::class,
        SerieEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HeroesDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun comicDao(): ComicDao
    abstract fun eventDao(): EventDao
    abstract fun serieDao(): SerieDao
}
