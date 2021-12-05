package com.example.proyecto.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentPrincipalBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject


class PrincipalFragment : Fragment() {
    lateinit var queue: RequestQueue
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
                val foto = response.getJSONObject("sprites").getString("front_default")
                val infoString = "Nombre: $nombre # Id: $id #Tipo:  $tipo # Peso: $peso # Hp: $hp #Ataque: $ataqye # Defensa: $defensa# Velocidad: $velocidad Foto: ${foto}"
                Log.d("JsonResponse", "response: $infoString")
                binding.mostrarNombre.setText(nombre)
                binding.mostrarTipo.setText(tipo)
                binding.mostrarHp.setText(hp.toString())
                binding.mostrarAtack.setText(ataqye.toString())
                binding.mostrarDefense.setText(defensa.toString())
                binding.mostrarSpeed.setText(velocidad.toString())
                binding.mostrarWeight.setText(peso.toString())
                Glide.with(this).load(foto).into(binding.imageView3)
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

}