package com.karimoh.pokemonkanto.ui.pokeskills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karimoh.pokemonkanto.model.api.Pokemon
import com.karimoh.pokemonkanto.model.pokemonAbilities.PokemonAbilities
import com.karimoh.pokemonkanto.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeSkillsViewModel() : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonInfoAbilities = MutableLiveData<PokemonAbilities>()

    fun getPokemonAbilities(id: Int){
        val call = service.getPokemonAbilities(id)

        call.enqueue(object : Callback<PokemonAbilities?> {
            override fun onResponse(
                call: Call<PokemonAbilities?>,
                response: Response<PokemonAbilities?>
            ) {
                response.body()?.let { pokemon ->
                    pokemonInfoAbilities.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<PokemonAbilities?>, t: Throwable) {
                call.cancel()
            }
        })
    }

}
