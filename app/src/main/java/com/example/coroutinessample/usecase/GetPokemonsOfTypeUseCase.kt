package com.example.coroutinessample.usecase

import com.example.coroutinessample.data.pokemon.PokemonRepository
import com.example.coroutinessample.domain.Pokemon
import com.example.coroutinessample.domain.PokemonType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.streams.toList

class GetPokemonsOfTypeUseCase(
    private val pokemonRepository: PokemonRepository
) {

    fun run(type: PokemonType, offset: Long = 0, numOfPokemonToFetch: Long = 5): Flow<Pokemon> = flow {
        type.pokemonIds.stream().skip(offset).limit(numOfPokemonToFetch).toList()
            .forEach {
                val details = pokemonRepository.getPokemon(it)
                emit(Pokemon(it, details.sprites.frontDefault))
            }
    }
}