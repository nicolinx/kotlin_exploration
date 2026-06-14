package com.example.bookshelfapp.core

import com.example.bookshelfapp.BuildConfig
import com.example.bookshelfapp.network.BooksApiService
import com.example.bookshelfapp.network.BooksRepository
import com.example.bookshelfapp.network.NetworkBooksRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = BuildConfig.BASE_URL
    private val apiKey = BuildConfig.API_KEY

    private val jsonParser = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(jsonParser.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val bookApiService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(bookApiService)
    }
}