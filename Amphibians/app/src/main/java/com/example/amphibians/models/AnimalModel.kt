package com.example.amphibians.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimalModel(
    val name: String,
    val type: String,
    val description: String,

    @SerialName("img_src")
    val imgSrc: String
)