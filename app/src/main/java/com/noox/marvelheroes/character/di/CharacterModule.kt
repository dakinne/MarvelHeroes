package com.noox.marvelheroes.character.di

import com.noox.marvelheroes.character.data.CharacterDataSource
import com.noox.marvelheroes.character.data.CharacterMapper
import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.usecase.GetCharacter
import com.noox.marvelheroes.character.ui.detail.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(
            characterId = characterId,
            getCharacter = get()
        )
    }

    single { GetCharacter(repository = get()) }
    single { CharacterMapper() }
    single { CharacterRepository(dataSource = get(), mapper = get()) }
    single { CharacterDataSource(apiService = get())}
}
