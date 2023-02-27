package com.example.coroutinessample.network.model
import com.google.gson.annotations.SerializedName


data class PokemonDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites,
)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?
)