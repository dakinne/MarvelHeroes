package com.noox.marvelheroes.comic.di

import com.noox.marvelheroes.comic.data.ComicMapper
import com.noox.marvelheroes.comic.data.api.ComicDataSource
import com.noox.marvelheroes.comic.data.ComicRepository
import com.noox.marvelheroes.comic.data.cache.ComicDao
import com.noox.marvelheroes.core.db.HeroesDatabase
import org.koin.dsl.module

val comicModule = module {

    single { comicDao(get()) }
    single { ComicMapper(imageMapper = get()) }
    single { ComicRepository(dataSource = get(), mapper = get(), dao = get()) }
    single { ComicDataSource(apiService = get()) }
}

private fun comicDao(heroesDatabase: HeroesDatabase): ComicDao = heroesDatabase.comicDao()
