package com.andresual.nexmedisassesment.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.andresual.nexmedisassesment.domain.model.Meal
import com.andresual.nexmedisassesment.domain.model.MealList
import com.andresual.nexmedisassesment.domain.usecase.GetMealList
import com.andresual.nexmedisassesment.domain.usecase.SearchMeal
import com.andresual.nexmedisassesment.presentation.base.BaseViewModel
import com.andresual.nexmedisassesment.presentation.screen.UiState
import com.andresual.nexmedisassesment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getList: GetMealList,
    private val getSearchMeal: SearchMeal
) : BaseViewModel() {

    private val _meals = MutableStateFlow(emptyList<Meal>())
    val meals get() = _meals.asStateFlow()

    private val _isSearchInitialized = MutableStateFlow(false)
    val isSearchInitialized get() = _isSearchInitialized.asStateFlow()

    private val _query = MutableStateFlow("")
    val query get() = _query.asStateFlow()

    private var isQueryChanged = false

    init {
        initRequest()
    }

    private suspend fun fetchList() {
        getList(
            search = "b"
        ).collect {response ->
            when (response) {
                is Resource.Success -> {
                    val mealList = (response.data as MealList).meals
                    _meals.value += mealList
                    areResponsesSuccessful.add(true)
                    isInitial = false
                }

                is Resource.Error -> {
                    errorText = response.message
                    areResponsesSuccessful.add(false)
                }
            }
        }
    }

    private suspend fun searchMeal() {
        getSearchMeal(
            search = query.value
        ).collect {response ->
            when (response) {
                is Resource.Success -> {
                    val mealList = (response.data as MealList).meals
                    _meals.value = mealList
                    areResponsesSuccessful.add(true)
                    isInitial = false
                }

                is Resource.Error -> {
                    errorText = response.message
                    areResponsesSuccessful.add(false)
                }
            }
        }
    }

    fun fetchInitialSearch(query: String) {
        _uiState.value = UiState.loadingState(isInitial)
        _isSearchInitialized.value = true
        _query.value = query

        isQueryChanged = true
        isInitial = true

        viewModelScope.launch {
            coroutineScope {
                searchMeal()
            }
            setUiState()
        }
    }

    fun clearSearchResults() {
        _isSearchInitialized.value = false
        _query.value = "b"

        viewModelScope.launch {
            coroutineScope {
                fetchList()
            }
        }
    }

    fun initRequest() {
        viewModelScope.launch {
            coroutineScope {
                launch {
                    fetchList()
                }
            }
            setUiState()
        }
    }
}