package com.example.coroutinessample

import android.app.Application
import com.example.coroutinessample.data.pokemon.PokemonDataSource
import com.example.coroutinessample.data.pokemon.PokemonRepository
import com.example.coroutinessample.data.type.PokemonTypeDataSource
import com.example.coroutinessample.data.type.PokemonTypeRepository
import com.example.coroutinessample.network.pokemon.PokemonNetworkDataSource
import com.example.coroutinessample.network.service.PokemonService
import com.example.coroutinessample.network.type.PokemonTypeNetworkDataSource
import com.example.coroutinessample.presentation.main.MainViewModel
import com.example.coroutinessample.usecase.GetPokemonTypesUseCase
import com.example.coroutinessample.usecase.GetPokemonsOfTypeUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MyApplication : Application() {

    private val viewModelModule = module {
        viewModelOf(::MainViewModel)
    }

    private val dataModule = module {
        single { PokemonTypeRepository(get()) }
        single { PokemonRepository(get()) }
    }

    private val networkModule = module {
        single<PokemonDataSource> { PokemonNetworkDataSource(get()) }
        single<PokemonTypeDataSource> { PokemonTypeNetworkDataSource(get()) }
        single { PokemonService.getInstance() }
    }

    private val useCaseModule = module {
        singleOf(::GetPokemonTypesUseCase)
        singleOf(::GetPokemonsOfTypeUseCase)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                viewModelModule,
                dataModule,
                networkModule,
                useCaseModule
            )
        }
    }
}