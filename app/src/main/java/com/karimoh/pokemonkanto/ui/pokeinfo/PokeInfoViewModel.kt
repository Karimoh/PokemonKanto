package com.karimoh.pokemonkanto.ui.pokeinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karimoh.pokemonkanto.model.api.Pokemon
import com.karimoh.pokemonkanto.model.pokemonSpecie.PokemonSpeciesData
import com.karimoh.pokemonkanto.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel() : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonInfoSpecie = MutableLiveData<PokemonSpeciesData>()

    fun getPokemonInfo(id: Int){
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getPokemonSpecie(id: Int){
        val call = service.getDataSpecies(id)

        call.enqueue(object : Callback<PokemonSpeciesData?> {
            override fun onResponse(
                call: Call<PokemonSpeciesData?>,
                response: Response<PokemonSpeciesData?>
            ) {
                response.body()?.let { pokemonSpecie ->
                    pokemonInfoSpecie.postValue(pokemonSpecie)
                }
            }

            override fun onFailure(call: Call<PokemonSpeciesData?>, t: Throwable) {
                call.cancel()
            }
        })
    }
}
