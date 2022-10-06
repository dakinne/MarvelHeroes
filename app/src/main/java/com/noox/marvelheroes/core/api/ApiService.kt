package com.noox.marvelheroes.core.api

import com.noox.marvelheroes.character.data.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(): CharacterDataWrapper

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDataWrapper
}
