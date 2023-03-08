package com.example.coroutinessample.domain

data class PokemonType(
    val id: Int,
    val name: String,
    val pokemonIds: List<Int> = emptyList()
)