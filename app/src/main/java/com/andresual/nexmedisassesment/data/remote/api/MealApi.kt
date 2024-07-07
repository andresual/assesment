package com.andresual.nexmedisassesment.data.remote.api

import com.andresual.nexmedisassesment.data.remote.dto.MealDetailDTO
import com.andresual.nexmedisassesment.data.remote.dto.MealListDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php?")
    suspend fun getMealList(
        @Query("f") search : String,
    ): MealListDTO

    @GET("search.php?")
    suspend fun searchMealList(
        @Query("s") search : String,
    ): MealListDTO

    @GET("lookup.php?")
    suspend fun getMealDetail(
        @Query("i") search : Int,
    ): MealDetailDTO
}