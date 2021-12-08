package com.example.proyecto.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.R
import com.example.proyecto.database.Pokemon
import com.example.proyecto.databinding.FragmentRightBinding
import com.example.proyecto.datos.GetDataModel
import com.example.proyecto.datos.RemoveDataModel
import com.example.proyecto.elements.DestinationAdapter
import com.example.proyecto.elements.Metodo


class RightFragment : Fragment(), Metodo {

    private lateinit var binding: FragmentRightBinding
    private val getDataModel: GetDataModel by viewModels()
    private val deleteDataModel: RemoveDataModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPokemonEntries.layoutManager = LinearLayoutManager(view?.context)
        getDataModel.getPokemon()
        getDataModel.savedPokemon.observe(viewLifecycleOwner, {pokemonlist->
            if(!pokemonlist.isNullOrEmpty()){
                val adapter = DestinationAdapter(pokemonlist, this)
                binding.rvPokemonEntries.adapter = adapter
                for (savepokemon in pokemonlist){

                }
            }else
            {

            }
        })

    }

    override fun onClick(pokemon: Pokemon){
        Log.d("mensaje", "estoy llegando")
        deleteDataModel.delete(pokemon)
        actualiza()
    }
    fun actualiza(){
        getDataModel.getPokemon()
        getDataModel.savedPokemon.observe(viewLifecycleOwner, {pokemonlist->
            if(!pokemonlist.isNullOrEmpty()){
                val adapter = DestinationAdapter(pokemonlist, this)
                binding.rvPokemonEntries.adapter = adapter
            }else
            {
                val adapter = DestinationAdapter(pokemonlist, this)
                binding.rvPokemonEntries.adapter = adapter
            }
        })
    }
}