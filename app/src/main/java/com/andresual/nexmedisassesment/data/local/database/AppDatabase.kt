package com.andresual.nexmedisassesment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andresual.nexmedisassesment.data.local.dao.MealDao
import com.andresual.nexmedisassesment.data.local.entity.FavoriteMealEntity

@Database(entities = [FavoriteMealEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}