package com.noox.marvelheroes.serie.di

import com.noox.marvelheroes.core.db.HeroesDatabase
import com.noox.marvelheroes.serie.data.SerieRepository
import com.noox.marvelheroes.serie.data.api.SerieDataSource
import com.noox.marvelheroes.serie.data.SerieMapper
import com.noox.marvelheroes.serie.data.cache.SerieDao
import org.koin.dsl.module

val serieModule = module {

    single { serieDao(get()) }
    single { SerieMapper(imageMapper = get()) }
    single { SerieRepository(dataSource = get(), mapper = get(), dao = get()) }
    single { SerieDataSource(apiService = get()) }
}

private fun serieDao(heroesDatabase: HeroesDatabase): SerieDao = heroesDatabase.serieDao()
