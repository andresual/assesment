package com.andresual.nexmedisassesment.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andresual.nexmedisassesment.data.local.entity.FavoriteMealEntity

@Dao
interface MealDao {

    @Query("SELECT * FROM favoritemealentity")
    suspend fun getAllMeal(): List<FavoriteMealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealEntity: FavoriteMealEntity)

    @Delete
    suspend fun deleteMeal(mealEntity: FavoriteMealEntity)

    @Query("SELECT EXISTS (SELECT * FROM favoritemealentity WHERE id=:id)")
    suspend fun mealExists(id: String): Boolean
}