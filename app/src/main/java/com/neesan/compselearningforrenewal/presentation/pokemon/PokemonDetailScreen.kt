package com.neesan.compselearningforrenewal.presentation.pokemon

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.neesan.compselearningforrenewal.domain.dataStore.pokemon.Sprites

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonById(1)
    }
    val uiState by viewModel.pokemonUiState.collectAsStateWithLifecycle()
    PokemonDetailContent(uiState = uiState)
}

@Composable
private fun PokemonDetailContent(uiState: PokemonUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.weight(1f),
            model = uiState.sprites.frontDefault,
            contentDescription = null
        )
        AsyncImage(
            modifier = Modifier.weight(1f),
            model = uiState.sprites.backDefault,
            contentDescription = null
        )
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
private fun PokemonDetailContentPreview() {
    PokemonDetailContent(
        uiState = PokemonUiState(
            name = "フシギダネ",
            weight = 69,
            height = 7,
            sprites = Sprites(
                frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                backDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"
            )
        )
    )
}