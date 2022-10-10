package com.noox.marvelheroes.comic.di

import com.noox.marvelheroes.comic.data.ComicDataSource
import com.noox.marvelheroes.comic.data.ComicMapper
import com.noox.marvelheroes.comic.data.ComicRepository
import org.koin.dsl.module

val comicModule = module {

    single { ComicMapper(imageMapper = get()) }
    single { ComicRepository(dataSource = get(), mapper = get()) }
    single { ComicDataSource(apiService = get()) }
}
