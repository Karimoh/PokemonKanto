package com.karimoh.pokemonkanto.ui.pokeevolutions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karimoh.pokemonkanto.R
import kotlinx.android.synthetic.main.activity_poeevolutions.*
import kotlinx.android.synthetic.main.activity_pokeinfo.nameTextView
import java.lang.StringBuilder


class PokeEvolutionsActivity : AppCompatActivity() {

    lateinit var viewModel: PokeEvolutionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poeevolutions)

        nameTextView.text = "LINEA EVOLUTIVA"

        viewModel = ViewModelProvider(this).get(PokeEvolutionsViewModel::class.java)

        initUI()

    }

    private fun initUI() {
        val url = intent.extras?.get("url") as String

        val array = Array(url.length) { url[it].toString() }

        for (i in 0..41){
            array[i]=""
        }
        val myStringBuilder = StringBuilder()
        for(i in array.indices){
            if(array[i] != ""){
                myStringBuilder.append(array[i])
            }
        }
        val url2 = myStringBuilder.toString()
        val url3 = url2.replace("/", "")

        viewModel.getPokemonEvolutions(url3)

        viewModel.pokemonInfoEvolutions.observe(this, Observer { pokemonEvolutions ->

            evolution1Text.text = pokemonEvolutions.chain.species.name

            if(pokemonEvolutions.chain.evolves_to.isEmpty()){
                evolution2Text.text = "----------"
                evolutions3Text.text = "----------"
            }else{
                evolution2Text.text = pokemonEvolutions.chain.evolves_to.first().species.name

                if(pokemonEvolutions.chain.evolves_to.first().evolves_to.isEmpty()){
                    evolutions3Text.text = "----------"
                }else{
                    evolutions3Text.text = pokemonEvolutions.chain.evolves_to.first().evolves_to.first().species.name
                }
            }
        })
    }
}

