package com.neesan.compselearningforrenewal.domain.useCase

import com.neesan.compselearningforrenewal.domain.dataStore.pokemon.PokemonDetail
import com.neesan.compselearningforrenewal.domain.reository.IPokemonRepository
import javax.inject.Inject

class FetchPokemonByIdUseCase @Inject constructor(private val pokemonRepository: IPokemonRepository) {
    suspend fun execute(pokemonId: Int): PokemonDetail = pokemonRepository.getPokemonDetailById(pokemonId)
}