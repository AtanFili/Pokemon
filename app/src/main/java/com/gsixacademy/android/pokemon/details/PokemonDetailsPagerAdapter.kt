package com.gsixacademy.android.pokemon.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PokemonDetailsPagerAdapter(fragmentManager: FragmentManager):FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
      if (position==0){
      return PokemonDetailsGeneralFragment()}
        else{
          return PokemonDetailsMovesFragment()
      }
    }



    override fun getCount(): Int {
       return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if(position==0){
            return "General"
        }else{
            return "Moves"
        }
    }
}



