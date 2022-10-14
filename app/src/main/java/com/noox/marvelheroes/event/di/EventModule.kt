package com.noox.marvelheroes.event.di

import com.noox.marvelheroes.core.db.HeroesDatabase
import com.noox.marvelheroes.event.data.api.EventDataSource
import com.noox.marvelheroes.event.data.EventMapper
import com.noox.marvelheroes.event.data.EventRepository
import com.noox.marvelheroes.event.data.cache.EventDao
import org.koin.dsl.module

val eventModule = module {

    single { eventDao(get()) }
    single { EventMapper(imageMapper = get()) }
    single { EventRepository(dataSource = get(), mapper = get(), dao = get()) }
    single { EventDataSource(apiService = get()) }
}

private fun eventDao(heroesDatabase: HeroesDatabase): EventDao = heroesDatabase.eventDao()
