package com.example.coroutinessample.data.pokemon

import com.example.coroutinessample.network.model.PokemonDetails

interface PokemonDataSource {

    suspend fun getPokemonDetails(id: Int): PokemonDetails
}