package com.example.proyecto.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_VERSION = 1
const val TABLE_POKEMON = "poemon"
const val DATABASE_NAME = "myappname.sqlite"

@Database(
    entities = [PokemonEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}