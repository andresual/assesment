package com.andresual.nexmedisassesment.di

import com.andresual.nexmedisassesment.data.repository.MealRepositoryImpl
import com.andresual.nexmedisassesment.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMealRepository(repository: MealRepositoryImpl): MealRepository
}