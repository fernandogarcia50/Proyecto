package com.example.proyecto.database

class Pokemon (id: Int, nombre: String, tipo:String, hp:Int, ataque:Int, defensa:Int, velocidad: Int, peso: Int, foto:String) {
    val id = id
    val nombre = nombre
    val tipo = tipo
    val hp = hp
    val ataque = ataque
    val defensa = defensa
    val velocidad = velocidad
    val peso = peso
    val foto = foto
}
fun Pokemon.toPokemonEntity()= PokemonEntity(
    id, nombre, tipo, hp, ataque, defensa, velocidad, peso, foto
)