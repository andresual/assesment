package com.andresual.nexmedisassesment.domain.usecase

import com.andresual.nexmedisassesment.domain.repository.MealRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavorite @Inject constructor(
    private val mealRepository: MealRepository
) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<List<Any>> = flow {
        emit(
            mealRepository.getFavoriteMeal()
        )
    }
}