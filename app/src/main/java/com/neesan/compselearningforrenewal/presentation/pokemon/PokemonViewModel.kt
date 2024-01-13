package com.neesan.compselearningforrenewal.presentation.pokemon

import androidx.lifecycle.ViewModel
import com.neesan.compselearningforrenewal.domain.useCase.FetchPokemonByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val fetchPokemonByIdUseCase: FetchPokemonByIdUseCase) :
    ViewModel() {
    private val _pokemonUiState = MutableStateFlow(PokemonUiState())
    val pokemonUiState: StateFlow<PokemonUiState> = _pokemonUiState

    suspend fun fetchPokemonById(pokemonId: Int) {
        val pokemonDetail = fetchPokemonByIdUseCase.execute(pokemonId)
        _pokemonUiState.update {
            _pokemonUiState.value.copy(
                isLoading = false,
                pokemonName = pokemonDetail.name,
                pokemonImageUrl = pokemonDetail.sprites.frontDefault,
                pokemonWeight = pokemonDetail.weight,
                pokemonHeight = pokemonDetail.height,
            )
        }
    }
}

data class PokemonUiState(
    val isLoading: Boolean = false,
    val pokemonName: String = "",
    val pokemonImageUrl: String = "",
    val pokemonWeight: Int = 0,
    val pokemonHeight: Int = 0,
    val pokemonBaseExperience: Int = 0,
    val pokemonAbilities: List<String> = emptyList(),
    val pokemonTypes: List<String> = emptyList(),
    val pokemonStats: List<String> = emptyList(),
    val pokemonMoves: List<String> = emptyList(),
    val pokemonError: String = ""
)