package com.example.coroutinessample.usecase

import com.example.coroutinessample.data.pokemon.PokemonRepository
import com.example.coroutinessample.data.type.PokemonTypeRepository
import com.example.coroutinessample.domain.Pokemon
import com.example.coroutinessample.domain.PokemonType

class GetPokemonTypesUseCase(
    private val pokemonTypeRepository: PokemonTypeRepository,
    private val pokemonRepository: PokemonRepository,
) {
    suspend fun run(numOfPokemonToFetch: Int = 20): List<PokemonType> {
        val output = mutableListOf<PokemonType>()

        val allTypes = pokemonTypeRepository.getAllTypes().results.map {
            Pair(it.getTypeId(), pokemonTypeRepository.getPokemonTypeDetails(it.getTypeId()))
        }
        allTypes.forEach {  (id, pokemonTypeDetails) ->
            val pokemons = mutableListOf<Pokemon>()
            pokemonTypeDetails.pokemon.map { it.pokemon }.take(numOfPokemonToFetch).forEach {  typePokemon ->
                val pokemonDetails = pokemonRepository.getPokemon(typePokemon.getPokemonId())
                pokemons.add(
                    Pokemon(
                        typePokemon.getPokemonId(),
                        pokemonDetails.sprites.frontDefault
                    )
                )
            }

            output.add(
                PokemonType(
                    id,
                    pokemonTypeDetails.name,
                    pokemons
                )
            )
        }

        return output
    }
}