package com.andresual.nexmedisassesment.domain.usecase

import com.andresual.nexmedisassesment.domain.repository.MealRepository
import javax.inject.Inject

class CheckFavorite @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(
        id: Int
    ): Boolean = mealRepository.mealExists(id)
}