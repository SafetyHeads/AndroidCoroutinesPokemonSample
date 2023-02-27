package com.example.coroutinessample.network.pokemon

import com.example.coroutinessample.data.pokemon.PokemonDataSource
import com.example.coroutinessample.network.model.PokemonDetails
import com.example.coroutinessample.network.service.PokemonService

class PokemonNetworkDataSource(
    private val pokemonService: PokemonService
) : PokemonDataSource {
    override suspend fun getPokemonDetails(id: Int): PokemonDetails {
        val response = pokemonService.getPokemonDetails(id)
        if (response.isSuccessful) {
            return response.body() ?: throw java.lang.IllegalArgumentException("Couldn't return PokemonDetails")
        } else {
            throw RuntimeException(response.errorBody()?.toString() ?: "Error fetching PokemonDetails:$id")
        }
    }
}