package com.noox.marvelheroes.character.di

import com.noox.marvelheroes.character.data.CharacterDataSource
import com.noox.marvelheroes.character.data.CharacterMapper
import com.noox.marvelheroes.character.data.CharacterRepository
import org.koin.dsl.module

val characterModule = module {

    single { CharacterMapper() }
    single { CharacterRepository(dataSource = get(), mapper = get()) }
    single { CharacterDataSource(apiService = get())}
}
