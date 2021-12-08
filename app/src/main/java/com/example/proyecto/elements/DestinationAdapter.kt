package com.example.proyecto.elements

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.database.Pokemon
import com.example.proyecto.databinding.PokemonItemBinding


class DestinationAdapter(private val pokemon : List<Pokemon>,  private val callBack: Metodo): RecyclerView.Adapter<DestinationAdapter.DestinationHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationHolder {
       val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationHolder(binding,callBack)
    }

    override fun onBindViewHolder(holder: DestinationHolder, position: Int) {
        holder.render(pokemon[position])
    }

    override fun getItemCount(): Int = pokemon.size


    class DestinationHolder(val binding: PokemonItemBinding,val callBack: Metodo) : RecyclerView.ViewHolder(binding.root){

        fun render (pokemon: Pokemon){
            binding.mostrarNombre.setText(pokemon.nombre)
            binding.mostrarTipo.setText(pokemon.tipo)
            binding.mostrarHp.setText(pokemon.hp.toString())
            binding.mostrarAtack.setText(pokemon.ataque.toString())
            binding.mostrarDefense.setText(pokemon.defensa.toString())
            binding.mostrarSpeed.setText(pokemon.velocidad.toString())
            binding.mostrarWeight.setText(pokemon.peso.toString())
            binding.mostrarID.setText(pokemon.id.toString())
            Glide.with(itemView).load(pokemon.foto).into(binding.imageView3)
            binding.eliminar.setOnClickListener {
                Log.d("pokemon selecc", "${pokemon.nombre}")
                callBack.onClick(pokemon)
            }
        }


    }
}