package com.example.coroutinessample.network.type

import com.example.coroutinessample.data.type.PokemonTypeDataSource
import com.example.coroutinessample.network.model.PokemonTypeDetails
import com.example.coroutinessample.network.model.PokemonTypes
import com.example.coroutinessample.network.service.PokemonService

class PokemonTypeNetworkDataSource(
    private val pokemonService: PokemonService
) : PokemonTypeDataSource {
    override suspend fun getAllTypes(): PokemonTypes {
        val response = pokemonService.getPokemonTypes()
        if (response.isSuccessful) {
            return response.body() ?: throw java.lang.IllegalArgumentException("Couldn't return PokemonTypes")
        } else {
            throw RuntimeException(response.errorBody()?.toString() ?: "Error fetching PokemonTypes")
        }
    }

    override suspend fun getPokemonTypeDetails(id: Int): PokemonTypeDetails {
        val response = pokemonService.getPokemonTypeDetails(id)
        if (response.isSuccessful) {
            return response.body() ?: throw java.lang.IllegalArgumentException("Couldn't return PokemonTypeDetails")
        } else {
            throw RuntimeException(response.errorBody()?.toString() ?: "Error fetching PokemonTypeDetails:$id")
        }
    }
}