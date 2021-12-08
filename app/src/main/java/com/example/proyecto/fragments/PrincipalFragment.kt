package com.example.proyecto.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.proyecto.R
import com.example.proyecto.database.Pokemon
import com.example.proyecto.databinding.FragmentPrincipalBinding
import com.example.proyecto.datos.SaveDataModel
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.security.Principal
import kotlin.math.log


class PrincipalFragment : Fragment() {
    lateinit var queue: RequestQueue
    private lateinit var foto: String
    private val saveDatamodel : SaveDataModel by viewModels()
    private lateinit var binding: FragmentPrincipalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPrincipalBinding.inflate(inflater, container, false)
        queue=Volley.newRequestQueue(activity)

        binding.accionBusqueda.setOnClickListener {
            if(!binding.busqueda.text.toString().isEmpty())
            {
                getPokemon(binding.busqueda.text.toString())
                binding.busqueda.text.clear()

            }

        }
        binding.guardarDatos.setOnClickListener {
            guardar()
            val destination= PrincipalFragmentDirections.actionPrincipalFragmentToRightFragment()
            NavHostFragment.findNavController(this).navigate(destination)
        }
        return binding.root
    }


    fun getPokemon(pokemonName: String){
        val url= "https://pokeapi.co/api/v2/pokemon/${pokemonName.lowercase()}"
        val jsonRequest = JsonObjectRequest(url,
            Response.Listener<JSONObject> { response->
               val id = response.getInt("id")
                val peso = response.getInt("weight")
                val tipo = response.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")
                val nombre = response.getString("name")
                val hp = response.getJSONArray("stats").getJSONObject(0).getInt("base_stat")
                val ataqye = response.getJSONArray("stats").getJSONObject(1).getInt("base_stat")
                val defensa = response.getJSONArray("stats").getJSONObject(2).getInt("base_stat")
                val velocidad = response.getJSONArray("stats").getJSONObject(5).getInt("base_stat")
                foto = response.getJSONObject("sprites").getString("front_default")
                val infoString = "Nombre: $nombre # Id: $id #Tipo:  $tipo # Peso: $peso # Hp: $hp #Ataque: $ataqye # Defensa: $defensa# Velocidad: $velocidad Foto: ${foto}"
                Log.d("JsonResponse", "response: $infoString")

                binding.mostrarNombre.setText(nombre)
                binding.mostrarTipo.setText(tipo)
                binding.mostrarHp.setText(hp.toString())
                binding.mostrarAtack.setText(ataqye.toString())
                binding.mostrarDefense.setText(defensa.toString())
                binding.mostrarSpeed.setText(velocidad.toString())
                binding.mostrarWeight.setText(peso.toString())
                binding.mostrarID.setText(id.toString())
                Glide.with(this).load(foto).into(binding.imageView3)
                binding.hp.isVisible=true
                binding.atack.isVisible=true
                binding.defense.isVisible=true
                binding.speed.isVisible=true
                binding.weight.isVisible=true
                binding.mostrarNombre.isVisible=true
                binding.mostrarTipo.isVisible=true
                binding.mostrarHp.isVisible=true
                binding.mostrarAtack.isVisible=true
                binding.mostrarDefense.isVisible=true
                binding.mostrarSpeed.isVisible=true
                binding.mostrarWeight.isVisible=true
                binding.mostrarID.isVisible=true
                binding.imageView3.isVisible=true
                binding.guardarDatos.isVisible=true

            },
            Response.ErrorListener { errorMessage ->
                Log.d("JsonResponse", "error: $errorMessage")

            })

        queue.add(jsonRequest)
    }
    override fun onStop() {
        super.onStop()
        queue.cancelAll("stopped")
    }
    private fun guardar(){
       saveDatamodel.save(Pokemon(
            binding.mostrarID.text.toString().toInt(),
            binding.mostrarNombre.text.toString(),
            binding.mostrarTipo.text.toString(),
            binding.mostrarHp.text.toString().toInt(),
            binding.mostrarAtack.text.toString().toInt(),
            binding.mostrarDefense.text.toString().toInt(),
            binding.mostrarSpeed.text.toString().toInt(),
            binding.mostrarWeight.text.toString().toInt(),
            foto
        ))

        Log.d("mensaje", "${foto}")
    }

}