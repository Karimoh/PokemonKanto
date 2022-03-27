package com.karimoh.pokemonkanto.service

import com.karimoh.pokemonkanto.model.api.PokeApiResponse
import com.karimoh.pokemonkanto.model.api.Pokemon
import com.karimoh.pokemonkanto.model.pokemonAbilities.PokemonAbilities
import com.karimoh.pokemonkanto.model.pokemonEvolutions.PokemonEvolutions
import com.karimoh.pokemonkanto.model.pokemonSpecie.PokemonSpeciesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>
    @GET("pokemon/{id}")
    fun getPokemonAbilities(@Path("id") id: Int): Call<PokemonAbilities>
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeApiResponse>
    @GET("pokemon-species/{id}")
    fun getDataSpecies(@Path("id") id: Int): Call<PokemonSpeciesData>
    @GET("evolution-chain/{id}")
    fun getPokemonEvolutions(@Path("id") id: String): Call<PokemonEvolutions>
}