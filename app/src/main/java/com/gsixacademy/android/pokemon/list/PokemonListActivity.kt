package com.gsixacademy.android.pokemon.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.gsixacademy.kotlinbasictutrorial.api.ServiceBuilder
import com.gsixacademy.android.pokemon.R
import com.gsixacademy.android.pokemon.api.PokemonApi
import com.gsixacademy.android.pokemon.details.PokemonDetailsActivity
import com.gsixacademy.android.pokemon.models.PokemonListResponse
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        val request=ServiceBuilder.buildService(PokemonApi::class.java)
        val call=request.getPokemonList(0,50)

        call.enqueue(object :Callback<PokemonListResponse>{
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"Api error",Toast.LENGTH_LONG)

            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                var pokemonListResponse=response.body()
                var pokemons=pokemonListResponse?.results
                if(pokemons!=null){
                    //initiate adapter
                var pokemonListAdapter=PokemonListAdapter(pokemons) {
                    if (it is PokemonListAdapterClickEvent.PokemonListAdapterItemClicked) {
                        startActivity(
                            Intent(
                                applicationContext,
                                PokemonDetailsActivity::class.java
                            ).putExtra("pokemonname", it.pokemonResult.name).putExtra("pokemonUrl",it.pokemonResult.url)
                        )
                    }
                }
                 recycler_view_pokemons.adapter=pokemonListAdapter
                }
            }


        })



    }
}
