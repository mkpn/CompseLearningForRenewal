package com.neesan.compselearningforrenewal.domain.dataStore.pokemon

import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/{id}")
    suspend fun getPokemonDetailById(@Path("id") pokemonId: Int): PokemonDetail
}