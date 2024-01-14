package com.neesan.compselearningforrenewal.domain.dataStore.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    // ここにポケモンの詳細情報に対応するデータフィールドを定義します
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val height: Int,
    val weight: Int,
)

@Serializable
data class Sprites(
    @SerialName("back_default")
    val backDefault: String = "",
    @SerialName("back_shiny")
    val backShiny: String = "",
    @SerialName("front_default")
    val frontDefault: String = "",
    @SerialName("front_shiny")
    val frontShiny: String = "",
)
