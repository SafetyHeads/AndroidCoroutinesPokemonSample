package com.example.coroutinessample.data.type

import com.example.coroutinessample.network.model.PokemonTypeDetails
import com.example.coroutinessample.network.model.PokemonTypes

interface PokemonTypeDataSource {
    suspend fun getAllTypes() : PokemonTypes
    suspend fun getPokemonTypeDetails(id: Int) : PokemonTypeDetails
}