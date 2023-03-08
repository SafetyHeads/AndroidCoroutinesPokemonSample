package com.example.coroutinessample.usecase

import com.example.coroutinessample.data.type.PokemonTypeRepository
import com.example.coroutinessample.domain.PokemonType

class GetPokemonTypesUseCase(
    private val pokemonTypeRepository: PokemonTypeRepository,
) {
    suspend fun run(): List<PokemonType> =
        pokemonTypeRepository.getAllTypes().results.map {
            Pair(it.getTypeId(), pokemonTypeRepository.getPokemonTypeDetails(it.getTypeId()))
        }.map { (id, details) ->
            PokemonType(id, details.name, details.pokemon.map { it.pokemon.getPokemonId() })
        }
}