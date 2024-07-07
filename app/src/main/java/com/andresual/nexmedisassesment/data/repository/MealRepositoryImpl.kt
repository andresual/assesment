package com.andresual.nexmedisassesment.data.repository

import com.andresual.nexmedisassesment.data.local.dao.MealDao
import com.andresual.nexmedisassesment.data.mapper.toFavoriteMeal
import com.andresual.nexmedisassesment.data.mapper.toFavoriteMealEntity
import com.andresual.nexmedisassesment.data.mapper.toMealDetail
import com.andresual.nexmedisassesment.data.mapper.toMealList
import com.andresual.nexmedisassesment.data.remote.api.MealApi
import com.andresual.nexmedisassesment.domain.model.FavoriteMeal
import com.andresual.nexmedisassesment.domain.model.MealDetail
import com.andresual.nexmedisassesment.domain.model.MealList
import com.andresual.nexmedisassesment.domain.repository.MealRepository
import com.andresual.nexmedisassesment.util.Resource
import com.andresual.nexmedisassesment.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val api: MealApi,
    private val safeApiCall: SafeApiCall,
    private val dao: MealDao
) : MealRepository {
    override suspend fun getMealList(search: String): Resource<MealList> = safeApiCall.execute {
        api.getMealList(search).toMealList()
    }

    override suspend fun getSearchMealList(search: String): Resource<MealList> = safeApiCall.execute {
        api.searchMealList(search).toMealList()
    }

    override suspend fun getMealDetail(id: Int): Resource<MealDetail> = safeApiCall.execute {
        api.getMealDetail(id).toMealDetail()
    }

    override suspend fun getFavoriteMeal(): List<FavoriteMeal> = dao.getAllMeal().map { it.toFavoriteMeal() }

    override suspend fun mealExists(mealId: Int): Boolean = dao.mealExists(mealId.toString())

    override suspend fun insertFavoriteMeal(favoriteMeal: FavoriteMeal) {
        dao.insertMeal(favoriteMeal.toFavoriteMealEntity())
    }

    override suspend fun deleteFavoriteMeal(favoriteMeal: FavoriteMeal) {
        dao.deleteMeal(favoriteMeal.toFavoriteMealEntity())
    }


}