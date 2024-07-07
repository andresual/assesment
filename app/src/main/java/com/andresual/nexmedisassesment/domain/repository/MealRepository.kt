package com.andresual.nexmedisassesment.domain.repository

import com.andresual.nexmedisassesment.domain.model.FavoriteMeal
import com.andresual.nexmedisassesment.domain.model.MealDetail
import com.andresual.nexmedisassesment.domain.model.MealList
import com.andresual.nexmedisassesment.util.Resource

interface MealRepository {
    suspend fun getMealList(search: String): Resource<MealList>
    suspend fun getSearchMealList(search: String): Resource<MealList>
    suspend fun getMealDetail(id: Int): Resource<MealDetail>
    suspend fun getFavoriteMeal(): List<FavoriteMeal>
    suspend fun mealExists(mealId: Int): Boolean
    suspend fun insertFavoriteMeal(favoriteMeal: FavoriteMeal)
    suspend fun deleteFavoriteMeal(favoriteMeal: FavoriteMeal)
}