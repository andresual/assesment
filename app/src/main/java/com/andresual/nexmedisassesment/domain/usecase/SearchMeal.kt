package com.andresual.nexmedisassesment.domain.usecase

import com.andresual.nexmedisassesment.domain.repository.MealRepository
import com.andresual.nexmedisassesment.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMeal @Inject constructor(
    private val mealRepository: MealRepository
) {
    operator fun invoke(
        search: String
    ): Flow<Resource<Any>> = flow {
        emit(
            mealRepository.getSearchMealList(search)
        )
    }
}