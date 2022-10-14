package com.noox.marvelheroes.core.api

import com.noox.marvelheroes.character.data.api.CharacterDataWrapper
import com.noox.marvelheroes.comic.data.api.ComicDataWrapper
import com.noox.marvelheroes.event.data.api.EventDataWrapper
import com.noox.marvelheroes.serie.data.api.SerieDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharacterDataWrapper

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDataWrapper

    @GET("characters/{id}/comics")
    suspend fun getComicsOfCharacter(
        @Path("id") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ComicDataWrapper

    @GET("characters/{id}/series")
    suspend fun getSeriesOfCharacter(
        @Path("id") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): SerieDataWrapper

    @GET("characters/{id}/events")
    suspend fun getEventsOfCharacter(
        @Path("id") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): EventDataWrapper
}
