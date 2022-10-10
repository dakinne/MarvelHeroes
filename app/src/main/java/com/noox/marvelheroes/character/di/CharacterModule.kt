package com.noox.marvelheroes.character.di

import com.noox.marvelheroes.character.data.CharacterDataSource
import com.noox.marvelheroes.character.data.CharacterMapper
import com.noox.marvelheroes.character.data.CharacterRepository
import com.noox.marvelheroes.character.domain.usecase.GetCharacter
import com.noox.marvelheroes.character.domain.usecase.GetPageOfCharacters
import com.noox.marvelheroes.character.ui.detail.CharacterDetailViewModel
import com.noox.marvelheroes.character.ui.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    viewModel {
        CharacterListViewModel(
            getPageOfCharacters = get()
        )
    }

    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(
            characterId = characterId,
            getCharacter = get()
        )
    }

    single { GetCharacter(
        characterRepository = get(),
        comicRepository = get(),
        serieRepository = get(),
        eventRepository = get())
    }
    single { GetPageOfCharacters(repository = get()) }
    single { CharacterMapper(imageMapper = get()) }
    single { CharacterRepository(dataSource = get(), mapper = get()) }
    single { CharacterDataSource(apiService = get())}
}
