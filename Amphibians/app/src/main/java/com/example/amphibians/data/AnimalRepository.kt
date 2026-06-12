package com.example.amphibians.data

import com.example.amphibians.models.AnimalModel

interface AnimalRepository {
    suspend fun getAmphibians(): List<AnimalModel>
}

class AnimalRepositoryImpl(
    private val apiService: AnimalApiService
) : AnimalRepository {
    override suspend fun getAmphibians(): List<AnimalModel> {
        return apiService.getAmphibians()
    }
}