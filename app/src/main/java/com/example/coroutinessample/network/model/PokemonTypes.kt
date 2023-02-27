package com.example.coroutinessample.network.model
import com.google.gson.annotations.SerializedName

data class PokemonTypes(
    @SerializedName("results")
    val results: List<Result>
)

data class Result(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    fun getTypeId(): Int {
        return url.split("/").last { it.isNotEmpty() }.toInt()
    }
}