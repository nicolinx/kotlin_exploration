package com.example.mycityapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item(
    val id: Int,
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    @DrawableRes val imageResId: Int,
    val categoryId: Int
)
