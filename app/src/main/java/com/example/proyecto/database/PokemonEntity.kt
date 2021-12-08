package com.example.proyecto.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_POKEMON)
data class PokemonEntity(
    @PrimaryKey val id:Int,
    val nombre: String,
    val tipo: String,
    val hp : Int,
    val ataque: Int,
    val defensa: Int,
    val velocidad: Int,
    val peso : Int,
    val foto : String
)
fun PokemonEntity.toPokemon() = Pokemon(
    id, nombre, tipo, hp, ataque, defensa, velocidad, peso, foto
)
