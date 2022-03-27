package com.karimoh.pokemonkanto.ui.pokeskills

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karimoh.pokemonkanto.R
import com.karimoh.pokemonkanto.model.pokemonAbilities.PokemonAbilities
import kotlinx.android.synthetic.main.activity_poeevolutions.*
import kotlinx.android.synthetic.main.activity_pokeinfo.*
import kotlinx.android.synthetic.main.activity_pokeskills.*
import java.lang.StringBuilder

class PokeSkillsActivity : AppCompatActivity() {

    lateinit var viewModel: PokeSkillsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeskills)

        nameTextViewAbilities.text = "HABILIDADES"

        viewModel = ViewModelProvider(this).get(PokeSkillsViewModel::class.java)

        initUI()

    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int


        viewModel.getPokemonAbilities(id)

        viewModel.pokemonInfoAbilities.observe(this, Observer { pokemonAbilities ->

            var array = arrayOfNulls<String>(3)
            var int = 0
            val myStringBuilder = StringBuilder()
            for(habilidades in pokemonAbilities.abilities){

                myStringBuilder.append(habilidades.ability.name)
                array[int] = myStringBuilder.toString()
                int++
                myStringBuilder.clear()
            }

            skill1TextView.text = array[0]

            skill2TextView.text = "----------"
            skill3TextView.text = "----------"

            if (array[1] != null || array[1] != ""){
                skill2TextView.text = array[1]

                if(array[2] != null || array[2] != ""){
                    skill3TextView.text = array[2]
                }
            }
        })
    }

}
