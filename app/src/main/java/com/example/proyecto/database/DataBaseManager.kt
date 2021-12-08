package com.example.proyecto.database

import android.content.Context
import androidx.room.Room

class DataBaseManager {
    lateinit var database: AppDataBase
    companion object{
        val instance = DataBaseManager()
    }
    fun initializeDb(context: Context){
        createDb(context)
    }
    private fun createDb(Context : Context){
        database = Room.databaseBuilder(Context, AppDataBase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
    }
}