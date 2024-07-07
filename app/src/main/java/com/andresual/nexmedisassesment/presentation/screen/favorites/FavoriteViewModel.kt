package com.andresual.nexmedisassesment.presentation.screen.favorites

import androidx.lifecycle.viewModelScope
import com.andresual.nexmedisassesment.domain.model.FavoriteMeal
import com.andresual.nexmedisassesment.domain.usecase.AddFavorite
import com.andresual.nexmedisassesment.domain.usecase.DeleteFavorite
import com.andresual.nexmedisassesment.domain.usecase.GetFavorite
import com.andresual.nexmedisassesment.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavorite: GetFavorite,
    private val deleteFavorite: DeleteFavorite,
    private val addFavorite: AddFavorite
) : BaseViewModel() {

    private val _favoriteMeal = MutableStateFlow(emptyList<FavoriteMeal>())
    val favoriteMeal get() = _favoriteMeal.asStateFlow()

    fun fetchFavoriteMeal() {
        viewModelScope.launch {
            getFavorite().collect {
                _favoriteMeal.value = it as List<FavoriteMeal>
            }
        }
    }

    fun removeMealFromFavorites(meal: FavoriteMeal) {
        viewModelScope.launch {
            deleteFavorite(favoriteMeal = meal)
            fetchFavoriteMeal()
        }
    }

    fun addMealToFavorites(meal: FavoriteMeal) {
        viewModelScope.launch {
            addFavorite(favoriteMeal = meal)
            fetchFavoriteMeal()
        }
    }

    init {
        fetchFavoriteMeal()
    }

}