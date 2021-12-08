package com.example.proyecto.datos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.database.DataBaseManager
import com.example.proyecto.database.MyAppDataSource
import com.example.proyecto.database.Pokemon
import kotlinx.coroutines.launch

class GetDataModel: ViewModel() {
    val savedPokemon= MutableLiveData<List<Pokemon>>()
    fun getPokemon(){
        viewModelScope.launch {
            val pokemonDao = DataBaseManager.instance.database.pokemonDao()
            savedPokemon.value = MyAppDataSource(pokemonDao).getPokemon().value
        }
    }
}