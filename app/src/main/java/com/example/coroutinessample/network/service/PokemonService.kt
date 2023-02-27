package com.example.coroutinessample.network.service

import com.example.coroutinessample.network.model.PokemonDetails
import com.example.coroutinessample.network.model.PokemonTypeDetails
import com.example.coroutinessample.network.model.PokemonTypes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonService {
    @GET("type")
    suspend fun getPokemonTypes(): Response<PokemonTypes>

    @GET("type/{id}")
    suspend fun getPokemonTypeDetails(@Path("id") id: Int): Response<PokemonTypeDetails>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): Response<PokemonDetails>

    companion object {
        private var retrofitService: PokemonService? = null
        fun getInstance() : PokemonService {
            if (retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(PokemonService::class.java)
            }
            return requireNotNull(retrofitService)
        }
    }
}