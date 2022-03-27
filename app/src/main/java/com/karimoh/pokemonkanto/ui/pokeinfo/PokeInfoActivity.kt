package com.karimoh.pokemonkanto.ui.pokeinfo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karimoh.pokemonkanto.R
import com.bumptech.glide.Glide
import com.karimoh.pokemonkanto.ui.pokeevolutions.PokeEvolutionsActivity
import com.karimoh.pokemonkanto.ui.pokeskills.PokeSkillsActivity
import kotlinx.android.synthetic.main.activity_pokeinfo.*
import java.lang.StringBuilder

class PokeInfoActivity : AppCompatActivity() {

    lateinit var viewModel: PokeInfoViewModel
    var urlPokemonEvolutions = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)

        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)

        initUI()
    }

    private fun initUI(){

        val id = intent.extras?.get("id") as Int


        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            nameTextView.text = pokemon.name

            Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
        })

        viewModel.getPokemonSpecie(id)

        viewModel.pokemonInfoSpecie.observe(this, Observer { pokemonSpecie ->
            nameTextView.text = pokemonSpecie.name
            happinessText.text = "Felicidad base: ${pokemonSpecie.base_happiness/10.0}"
            captureRateText.text = "Ratio de captuea: ${pokemonSpecie.capture_rate/10.0}"
            colorText.text = "Color: ${pokemonSpecie.color.name}"
            urlPokemonEvolutions = "${pokemonSpecie.evolution_chain.url}"

            val myStringBuilder = StringBuilder()
            for(grupo in pokemonSpecie.egg_groups){
                myStringBuilder.append(grupo.name + ", ")
            }

            groupEggsText.text = "Grupo de huevos: ${myStringBuilder}"
        })

        val boton1=findViewById<Button>(R.id.evolutionButton)
        boton1.setOnClickListener {
            val intent = Intent(this, PokeEvolutionsActivity::class.java)
            intent.putExtra("url", urlPokemonEvolutions)
            startActivity(intent)
        }

        val boton2=findViewById<Button>(R.id.skillsButton)
        boton2.setOnClickListener {
            val intent = Intent(this, PokeSkillsActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

    }
}


