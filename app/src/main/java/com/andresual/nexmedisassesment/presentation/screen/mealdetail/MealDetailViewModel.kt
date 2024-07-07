package com.andresual.nexmedisassesment.presentation.screen.mealdetail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.andresual.nexmedisassesment.domain.model.FavoriteMeal
import com.andresual.nexmedisassesment.domain.model.MealDetail
import com.andresual.nexmedisassesment.domain.usecase.AddFavorite
import com.andresual.nexmedisassesment.domain.usecase.CheckFavorite
import com.andresual.nexmedisassesment.domain.usecase.DeleteFavorite
import com.andresual.nexmedisassesment.domain.usecase.GetDetail
import com.andresual.nexmedisassesment.presentation.base.BaseViewModel
import com.andresual.nexmedisassesment.presentation.screen.UiState
import com.andresual.nexmedisassesment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getDetail: GetDetail,
    private val checkFavorites: CheckFavorite,
    private val deleteFavorite: DeleteFavorite,
    private val addFavorite: AddFavorite
): BaseViewModel() {

    private val _details = MutableStateFlow(MealDetail.empty)
    val details get() = _details.asStateFlow()

    private val _isInFavorites = MutableStateFlow(false)
    val isInFavorites get() = _isInFavorites.asStateFlow()

    private lateinit var favoriteMeal: FavoriteMeal

    private fun fetchMealDetail() {
        viewModelScope.launch {
            getDetail(detailId).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        (response.data as MealDetail).apply {
                            _details.value = this

                            favoriteMeal = FavoriteMeal(
                                idMeal = meals[0].idMeal ?: "",
                                strImageSource = meals[0].strImageSource ?: "",
                                strCategory = meals[0].strCategory ?: "",
                                strArea = meals[0].strArea ?: "",
                                strCreativeCommonsConfirmed = meals[0].strCreativeCommonsConfirmed ?: "",
                                strTags = meals[0].strTags ?: "",
                                strInstructions = meals[0].strInstructions  ?: "",
                                strMealThumb = meals[0].strMealThumb  ?: "",
                                strYoutube = meals[0].strYoutube  ?: "",
                                strMeal = meals[0].strMeal ?: "",
                                strDrinkAlternate = meals[0].strDrinkAlternate ?: "",
                                dateModified = meals[0].dateModified ?: "",
                                strSource = meals[0].strSource ?: "",
                                strIngredient1= meals[0].strIngredient1 ?: "",
                                strIngredient2= meals[0].strIngredient2 ?: "",
                                strIngredient3= meals[0].strIngredient3 ?: "",
                                strIngredient4= meals[0].strIngredient4 ?: "",
                                strIngredient5= meals[0].strIngredient5 ?: "",
                                strIngredient6= meals[0].strIngredient6 ?: "",
                                strIngredient7= meals[0].strIngredient7 ?: "",
                                strIngredient8= meals[0].strIngredient8 ?: "",
                                strIngredient9= meals[0].strIngredient9 ?: "",
                                strIngredient10= meals[0].strIngredient10 ?: "",
                                strIngredient11= meals[0].strIngredient11 ?: "",
                                strIngredient12= meals[0].strIngredient12 ?: "",
                                strIngredient13= meals[0].strIngredient13 ?: "",
                                strIngredient14= meals[0].strIngredient14 ?: "",
                                strIngredient15= meals[0].strIngredient15 ?: "",
                                strIngredient16= meals[0].strIngredient16 ?: "",
                                strIngredient17= meals[0].strIngredient17 ?: "",
                                strIngredient18= meals[0].strIngredient18 ?: "",
                                strIngredient19= meals[0].strIngredient19 ?: "",
                                strIngredient20= meals[0].strIngredient20 ?: "",
                                strMeasure1= meals[0].strMeasure1 ?: "",
                                strMeasure2= meals[0].strMeasure2 ?: "",
                                strMeasure3= meals[0].strMeasure3 ?: "",
                                strMeasure4= meals[0].strMeasure4 ?: "",
                                strMeasure5= meals[0].strMeasure5 ?: "",
                                strMeasure6= meals[0].strMeasure6 ?: "",
                                strMeasure7= meals[0].strMeasure7 ?: "",
                                strMeasure8= meals[0].strMeasure8 ?: "",
                                strMeasure9= meals[0].strMeasure9 ?: "",
                                strMeasure10= meals[0].strMeasure10 ?: "",
                                strMeasure11= meals[0].strMeasure11 ?: "",
                                strMeasure12= meals[0].strMeasure12 ?: "",
                                strMeasure13= meals[0].strMeasure13 ?: "",
                                strMeasure14= meals[0].strMeasure14 ?: "",
                                strMeasure15= meals[0].strMeasure15 ?: "",
                                strMeasure16= meals[0].strMeasure16 ?: "",
                                strMeasure17= meals[0].strMeasure17 ?: "",
                                strMeasure18= meals[0].strMeasure18 ?: "",
                                strMeasure19= meals[0].strMeasure19 ?: "",
                                strMeasure20= meals[0].strMeasure20 ?: ""
                            )
                        }
                        _uiState.value = UiState.successState()
                    }

                    is Resource.Error -> {
                        _uiState.value = UiState.errorState(errorText = response.message)
                    }
                }
            }
        }
    }

    private fun checkFavorites() {
        viewModelScope.launch {
            _isInFavorites.value = checkFavorites(detailId)
        }
    }

    fun updateFavorites() {
        viewModelScope.launch {
            if (_isInFavorites.value) {
                deleteFavorite(favoriteMeal = favoriteMeal)
                _isInFavorites.value = false
            } else {
                addFavorite(favoriteMeal = favoriteMeal)
                _isInFavorites.value = true
            }
        }
    }

    fun initRequests(mealId: Int) {
        detailId = mealId
        checkFavorites()
        fetchMealDetail()
    }
}