package com.example.coroutinessample.domain

data class PokemonType(
    val id: Int,
    val name: String,
    val pokemons: List<Pokemon> = emptyList()
)