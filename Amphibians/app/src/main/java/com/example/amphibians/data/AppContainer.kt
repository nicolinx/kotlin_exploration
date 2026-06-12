package com.example.amphibians.data

import com.example.amphibians.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val animalRepository: AnimalRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = BuildConfig.BASE_URL;

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val animalApiService: AnimalApiService by lazy {
        retrofit.create(AnimalApiService::class.java)
    }

    override val animalRepository: AnimalRepository by lazy {
        AnimalRepositoryImpl(animalApiService)
    }
}