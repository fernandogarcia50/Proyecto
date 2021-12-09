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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject


class RightFragment : Fragment(), Metodo {

    private lateinit var binding: FragmentRightBinding
    private lateinit var database: DatabaseReference
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
                val adapter = DestinationAdapter(pokemonlist, this)
                binding.rvPokemonEntries.adapter = adapter
            }
        })

    }

    override fun onClick(pokemon: Pokemon){
        Log.d("mensaje", "estoy llegando")
        getDataModel.delete(pokemon)
        actualizarPerfil()

    }
    private fun actualizarPerfil(){
        val myDB= FirebaseDatabase.getInstance()
        database=myDB.reference
        var pokemon:Int=0
        database.child("usuarios").child("1").get().addOnSuccessListener { record ->

            if (record.exists()) {
                val json = JSONObject(record.value.toString())
                pokemon=json.getInt("pokemon")
                if(pokemon > 0){
                    val actualizarPok = hashMapOf<String, Any>(
                        "/usuarios/1/pokemon" to pokemon-1
                    )
                    Log.d("nuevop", "${pokemon}")
                    database.updateChildren(actualizarPok)
                }
            }else{
                Log.d("Mensaje", "No se encontró el usuario")
            }
        }


    }


}