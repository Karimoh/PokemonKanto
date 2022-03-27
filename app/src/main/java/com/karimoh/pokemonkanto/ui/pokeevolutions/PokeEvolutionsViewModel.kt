package com.karimoh.pokemonkanto.ui.pokeevolutions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karimoh.pokemonkanto.model.api.Pokemon
import com.karimoh.pokemonkanto.model.pokemonEvolutions.Chain
import com.karimoh.pokemonkanto.model.pokemonEvolutions.EvolvesTo
import com.karimoh.pokemonkanto.model.pokemonEvolutions.PokemonEvolutions
import com.karimoh.pokemonkanto.model.pokemonSpecie.PokemonSpeciesData
import com.karimoh.pokemonkanto.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeEvolutionsViewModel() : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonInfoEvolutions = MutableLiveData<PokemonEvolutions>()

    fun getPokemonEvolutions(id: String){
        val call = service.getPokemonEvolutions(id)

        call.enqueue(object : Callback<PokemonEvolutions?> {
            override fun onResponse(
                call: Call<PokemonEvolutions?>,
                response: Response<PokemonEvolutions?>
            ) {
                response.body()?.let { pokemonEvolutions ->
                    pokemonInfoEvolutions.postValue(pokemonEvolutions)
                }
            }

            override fun onFailure(call: Call<PokemonEvolutions?>, t: Throwable) {
                call.cancel()
            }
        })
    }

}
