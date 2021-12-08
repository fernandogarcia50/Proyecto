package com.example.proyecto.database

import androidx.room.*

@Dao
interface PokemonDao {
    @Delete
    fun delete(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(pokemon: PokemonEntity)

    @Query("SELECT * FROM $TABLE_POKEMON")
    fun getPokemonFromDatabase(): List<PokemonEntity>
}