package com.andresual.nexmedisassesment.domain.usecase

import com.andresual.nexmedisassesment.domain.model.FavoriteMeal
import com.andresual.nexmedisassesment.domain.repository.MealRepository
import javax.inject.Inject

class DeleteFavorite @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(
        favoriteMeal: FavoriteMeal? = null,
    ) {
        mealRepository.deleteFavoriteMeal(favoriteMeal!!)
    }
}