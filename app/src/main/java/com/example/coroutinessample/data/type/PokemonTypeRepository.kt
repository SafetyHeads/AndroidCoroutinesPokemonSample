package com.example.coroutinessample.data.type

import com.example.coroutinessample.network.model.PokemonTypeDetails
import com.example.coroutinessample.network.model.PokemonTypes

class PokemonTypeRepository(
    private val dataSource: PokemonTypeDataSource
) {
    suspend fun getAllTypes() : PokemonTypes = dataSource.getAllTypes()

    suspend fun getPokemonTypeDetails(id: Int) : PokemonTypeDetails = dataSource.getPokemonTypeDetails(id)
}