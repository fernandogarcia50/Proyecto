package com.example.proyecto.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentLeftBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject


class LeftFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var binding: FragmentLeftBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeftBinding.inflate(inflater,container,false)
        val myDB= FirebaseDatabase.getInstance()
        database=myDB.reference

        database.child("usuarios").child("1").get().addOnSuccessListener { record ->

            if (record.exists()) {
                Log.d("resultado", "${record.value}")
                val json = JSONObject(record.value.toString())
                Log.d("resultado", "${json}")
                binding.mostrarNombre.setText("${json.getString("nombre")} ${json.getString("apellido")}")
                binding.mostrarUsuario.setText("${json.getString("usuario")}")
                binding.mostrarNivel.setText("${json.getInt("nivel")}")
                binding.mostrarPokemon.setText("${json.getInt("pokemon")}")
            }else{
               Log.d("Mensaje", "No se encontró el usuario")
            }
        }
        return binding.root
    }


}
class User(name:String,lastName: String, userName:String, nivel: Int, pokemon: Int)
{
    val nombre=name
    val apellido=lastName
    val usuario= userName
    val nivel = nivel
    val pokemon = pokemon
}