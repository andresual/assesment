package com.andresual.nexmedisassesment.di

import android.content.Context
import androidx.room.Room
import com.andresual.nexmedisassesment.data.local.dao.MealDao
import com.andresual.nexmedisassesment.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMealDao(appDatabase: AppDatabase): MealDao = appDatabase.mealDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "meal-app-db"
        ).build()
}