package com.neesan.compselearningforrenewal.presentation.pokemon

import androidx.lifecycle.ViewModel
import com.neesan.compselearningforrenewal.domain.dataStore.pokemon.Sprites
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
                name = pokemonDetail.name,
                weight = pokemonDetail.weight,
                height = pokemonDetail.height,
                sprites = pokemonDetail.sprites
            )
        }
    }
}

data class PokemonUiState(
    val isLoading: Boolean = true,
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val sprites: Sprites = Sprites()
)