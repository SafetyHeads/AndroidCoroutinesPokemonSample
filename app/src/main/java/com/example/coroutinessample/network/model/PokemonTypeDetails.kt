package com.example.coroutinessample.network.model
import com.google.gson.annotations.SerializedName


data class PokemonTypeDetails(
    @SerializedName("name")
    val name: String,
    @SerializedName("pokemon")
    val pokemon: List<PokemonListItem>
)

data class PokemonListItem(
    @SerializedName("pokemon")
    val pokemon: TypePokemon
)

data class TypePokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    fun getPokemonId(): Int = url.split("/").last { it.isNotEmpty() }.toInt()
}