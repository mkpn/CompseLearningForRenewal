package com.neesan.compselearningforrenewal.domain.reository

import com.neesan.compselearningforrenewal.domain.dataStore.pokemon.PokeApi
import com.neesan.compselearningforrenewal.domain.dataStore.pokemon.PokemonDetail
import javax.inject.Inject

interface IPokemonRepository {
    suspend fun getPokemonDetailById(pokemonId: Int): PokemonDetail
}

class PokemonRepository @Inject constructor(private val api: PokeApi) : IPokemonRepository {
    override suspend fun getPokemonDetailById(pokemonId:Int): PokemonDetail {
        return api.getPokemonDetailById(pokemonId)
    }
}