package com.example.gridpractice.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val totalCourse: Int,
    @DrawableRes val imageId: Int,
)
