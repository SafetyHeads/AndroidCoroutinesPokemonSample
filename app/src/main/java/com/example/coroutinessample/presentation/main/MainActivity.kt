package com.example.coroutinessample.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coroutinessample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm : MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setupPokemonTypesRecyclerView()
        setupPokemonRecyclerView()
    }

    private fun setupPokemonTypesRecyclerView() {
        vm.getPokemonTypes().observe(this) { pokemonTypes ->
            binding.types.apply {
                adapter = PokemonTypeAdapter(pokemonTypes) { clickedPokemonType ->
                    vm.selectPokemonType(clickedPokemonType)
                }
            }
        }
    }

    private fun setupPokemonRecyclerView() {
        binding.pokemons.layoutManager = GridLayoutManager(this, 3)
        vm.getSelectedPokemonType().observe(this) { selectedPokemonType ->
                binding.pokemons.adapter = PokemonAdapter(selectedPokemonType.pokemons)
        }
    }
}