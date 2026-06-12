package com.example.amphibians.data

import com.example.amphibians.models.AnimalModel
import retrofit2.http.GET

interface AnimalApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AnimalModel>
}