package com.neesan.compselearningforrenewal

import com.neesan.compselearningforrenewal.domain.dataStore.pokemon.PokeApi
import dagger.Module
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ExternalModule {
    @Provides
    fun providePokeApi(
        // Potential dependencies of this type
    ): PokeApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") // PokeAPIのベースURL
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())) // JSONを扱うためのコンバーター
            .client(client)
            .build()

        return retrofit.create(PokeApi::class.java)
    }
}