package com.example.proyecto.datos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.database.DataBaseManager
import com.example.proyecto.database.MyAppDataSource
import com.example.proyecto.database.Pokemon
import kotlinx.coroutines.launch

class RemoveDataModel : ViewModel() {
    fun delete(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao = DataBaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).delete(pokemon)
        }
    }
}