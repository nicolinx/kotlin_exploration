package com.example.mycityapp.data

import com.example.mycityapp.R
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Item

object LocalDataProvider {
    fun getCategoriesData(): List<Category> {
        return listOf(
            Category(
                id = 1,
                titleResId = R.string.category_coffee_title,
                descriptionResId = R.string.category_coffee_desc,
                imageResId = R.drawable.kopi,
            ),
            Category(
                id = 2,
                titleResId = R.string.category_restaurant_title,
                descriptionResId = R.string.category_restaurant_desc,
                imageResId = R.drawable.restaurant,
            ),
            Category(
                id = 3,
                titleResId = R.string.category_kid_title,
                descriptionResId = R.string.category_kid_desc,
                imageResId = R.drawable.kid,
            ),
            Category(
                id = 4,
                titleResId = R.string.category_shop_title,
                descriptionResId = R.string.category_shop_desc,
                imageResId = R.drawable.shop,
            )
        )
    }

    fun getItemsData(): List<Item> {
        return listOf(
            Item(
                id = 1,
                titleResId = R.string.coffee_toko_jawa_title,
                descriptionResId = R.string.coffee_toko_jawa_desc,
                imageResId = R.drawable.kopi_toko_jawa,
                categoryId = 1
            ),
            Item(
                id = 2,
                titleResId = R.string.coffee_anomali_title,
                descriptionResId = R.string.coffee_anomali_desc,
                imageResId = R.drawable.kopi_anomali,
                categoryId = 1
            ),
            Item(
                id = 3,
                titleResId = R.string.coffee_lantai_atas_title,
                descriptionResId = R.string.coffee_lantai_atas_desc,
                imageResId = R.drawable.kopi_lantai_atas,
                categoryId = 1
            ),
            Item(
                id = 4,
                titleResId = R.string.restaurant_menteng_title,
                descriptionResId = R.string.restaurant_menteng_desc,
                imageResId = R.drawable.restaurant_menteng,
                categoryId = 2
            ),
            Item(
                id = 5,
                titleResId = R.string.restaurant_henshin_title,
                descriptionResId = R.string.restaurant_henshin_desc,
                imageResId = R.drawable.restaurant_henshin,
                categoryId = 2
            ),
            Item(
                id = 6,
                titleResId = R.string.restaurant_soto_title,
                descriptionResId = R.string.restaurant_soto_desc,
                imageResId = R.drawable.restaurant_soto,
                categoryId = 2
            ),
            Item(
                id = 7,
                titleResId = R.string.kid_aquarium_title,
                descriptionResId = R.string.kid_aquarium_desc,
                imageResId = R.drawable.kid_aquarium,
                categoryId = 3
            ),
            Item(
                id = 8,
                titleResId = R.string.kid_kidzania_title,
                descriptionResId = R.string.kid_kidzania_desc,
                imageResId = R.drawable.kid_kidzania,
                categoryId = 3
            ),
            Item(
                id = 9,
                titleResId = R.string.kid_moja_title,
                descriptionResId = R.string.kid_moja_desc,
                imageResId = R.drawable.kid_moja,
                categoryId = 3
            ),
            Item(
                id = 10,
                titleResId = R.string.shop_ashta_title,
                descriptionResId = R.string.shop_ashta_desc,
                imageResId = R.drawable.shop_ashta_district_8,
                categoryId = 4
            ),
            Item(
                id = 11,
                titleResId = R.string.shop_gi_title,
                descriptionResId = R.string.shop_gi_desc,
                imageResId = R.drawable.shop_grand_indonesia,
                categoryId = 4
            ),
            Item(
                id = 12,
                titleResId = R.string.shop_pi_title,
                descriptionResId = R.string.shop_pi_desc,
                imageResId = R.drawable.shop_plaza_indonesia,
                categoryId = 4
            ),
        )
    }
}