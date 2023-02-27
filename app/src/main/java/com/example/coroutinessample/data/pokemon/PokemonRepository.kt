package com.example.coroutinessample.data.pokemon

import com.example.coroutinessample.network.model.PokemonDetails

class PokemonRepository(
    private val dataSource: PokemonDataSource
) {
    suspend fun getPokemon(id: Int): PokemonDetails = dataSource.getPokemonDetails(id)
}