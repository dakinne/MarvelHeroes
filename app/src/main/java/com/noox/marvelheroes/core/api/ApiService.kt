package com.noox.marvelheroes.core.api

import com.noox.marvelheroes.character.data.CharacterDataWrapper
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
}
