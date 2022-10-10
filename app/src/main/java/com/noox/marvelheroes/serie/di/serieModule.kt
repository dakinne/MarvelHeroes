package com.noox.marvelheroes.serie.di

import com.noox.marvelheroes.serie.data.SerieRepository
import com.noox.marvelheroes.serie.data.SerieDataSource
import com.noox.marvelheroes.serie.data.SerieMapper
import org.koin.dsl.module

val serieModule = module {

    single { SerieMapper(imageMapper = get()) }
    single { SerieRepository(dataSource = get(), mapper = get()) }
    single { SerieDataSource(apiService = get()) }
}
