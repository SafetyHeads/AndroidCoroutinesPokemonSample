package com.example.coroutinessample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinessample.domain.Pokemon
import com.example.coroutinessample.domain.PokemonType
import com.example.coroutinessample.usecase.GetPokemonTypesUseCase
import com.example.coroutinessample.usecase.GetPokemonsOfTypeUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPokemonTypesUseCase: GetPokemonTypesUseCase,
    private val getPokemonsOfTypeUseCase: GetPokemonsOfTypeUseCase
) : ViewModel() {

    private var selectedPokemonType: PokemonType? = null
    private val pokemonTypes = MutableLiveData<List<PokemonType>>()
    private val pokemonesToDisplay = MutableLiveData<List<Pokemon>>()

    init {
        viewModelScope.launch {
            pokemonTypes.postValue(getPokemonTypesUseCase.run())
        }
    }

    fun getPokemonTypes() = pokemonTypes as LiveData<List<PokemonType>>

    fun getPokemonsToDisplay() = pokemonesToDisplay as LiveData<List<Pokemon>>

    fun selectPokemonType(type: PokemonType) {
        if (type != selectedPokemonType) {
            pokemonesToDisplay.postValue(emptyList())
        }
        selectedPokemonType = type
        viewModelScope.launch {
            getPokemonsOfTypeUseCase.run(type, (pokemonesToDisplay.value?.size ?: 0).toLong())
                .collect {
                    val newPokemons = (pokemonesToDisplay.value ?: emptyList()) + it
                    pokemonesToDisplay.postValue(newPokemons)
                }
        }
    }
}