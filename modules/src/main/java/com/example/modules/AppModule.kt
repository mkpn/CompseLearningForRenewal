package com.neesan.compselearningforrenewal

import com.neesan.compselearningforrenewal.domain.reository.IPokemonRepository
import com.neesan.compselearningforrenewal.domain.reository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsPokemonRepository(
        pokemonRepository: PokemonRepository,
    ): IPokemonRepository


}