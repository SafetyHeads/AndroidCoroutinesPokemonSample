package com.example.coroutinessample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinessample.domain.PokemonType
import com.example.coroutinessample.usecase.GetPokemonTypesUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPokemonTypesUseCase: GetPokemonTypesUseCase
) : ViewModel() {

    private lateinit var fetchedPokemonTypes: List<PokemonType>

    private val pokemonTypes = MutableLiveData<List<PokemonType>>()
    private val selectedPokemonType = MutableLiveData<PokemonType>()

    init {
        viewModelScope.launch {
            fetchedPokemonTypes = getPokemonTypesUseCase.run()
            pokemonTypes.postValue(fetchedPokemonTypes)
        }
    }

    fun getPokemonTypes() = pokemonTypes as LiveData<List<PokemonType>>

    fun getSelectedPokemonType() = selectedPokemonType as LiveData<PokemonType>

    fun selectPokemonType(type: PokemonType) {
        if (fetchedPokemonTypes.contains(type)) {
            selectedPokemonType.postValue(type)
        }
    }
}