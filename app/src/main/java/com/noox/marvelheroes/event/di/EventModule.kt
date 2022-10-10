package com.noox.marvelheroes.event.di

import com.noox.marvelheroes.event.data.EventDataSource
import com.noox.marvelheroes.event.data.EventMapper
import com.noox.marvelheroes.event.data.EventRepository
import org.koin.dsl.module

val eventModule = module {

    single { EventMapper(imageMapper = get()) }
    single { EventRepository(dataSource = get(), mapper = get()) }
    single { EventDataSource(apiService = get()) }
}
