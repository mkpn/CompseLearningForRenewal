package com.neesan.compselearningforrenewal.presentation.pokemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonById(1)
    }
    PokemonDetailContent()
}

@Composable
private fun PokemonDetailContent() {

}