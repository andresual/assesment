package com.andresual.nexmedisassesment.data.mapper

import com.andresual.nexmedisassesment.data.remote.dto.MealDTO
import com.andresual.nexmedisassesment.data.remote.dto.MealDetailDTO
import com.andresual.nexmedisassesment.data.remote.dto.MealDetailItemDTO
import com.andresual.nexmedisassesment.data.remote.dto.MealListDTO
import com.andresual.nexmedisassesment.domain.model.Meal
import com.andresual.nexmedisassesment.domain.model.MealDetail
import com.andresual.nexmedisassesment.domain.model.MealDetailItem
import com.andresual.nexmedisassesment.domain.model.MealList

fun MealListDTO.toMealList(): MealList = MealList(
    meals = meals.map { it.toMeal() }
)

fun MealDetailDTO.toMealDetail(): MealDetail = MealDetail(
    meals = meals.map { it.toMealDetail() }
)

fun MealDTO.toMeal(): Meal = Meal(
    idMeal = idMeal,
    strMeal = strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags,
    strYoutube = strYoutube,
    strIngredient1 = strIngredient1,
    strIngredient2 = strIngredient2,
    strIngredient3 = strIngredient3,
    strIngredient4 = strIngredient4,
    strIngredient5 = strIngredient5,
    strIngredient6 = strIngredient6,
    strIngredient7 = strIngredient7,
    strIngredient8 = strIngredient8,
    strIngredient9 = strIngredient9,
    strIngredient10 = strIngredient10,
    strIngredient11 = strIngredient11,
    strIngredient12 = strIngredient12,
    strIngredient13 = strIngredient13,
    strIngredient14 = strIngredient14,
    strIngredient15 = strIngredient15,
    strIngredient16 = strIngredient16,
    strIngredient17 = strIngredient17,
    strIngredient18 = strIngredient18,
    strIngredient19 = strIngredient19,
    strIngredient20 = strIngredient20,
    strMeasure1 = strMeasure1,
    strMeasure2 = strMeasure2,
    strMeasure3 = strMeasure3,
    strMeasure4 = strMeasure4,
    strMeasure5 = strMeasure5,
    strMeasure6 = strMeasure6,
    strMeasure7 = strMeasure7,
    strMeasure8 = strMeasure8,
    strMeasure9 = strMeasure9,
    strMeasure10 = strMeasure10,
    strMeasure11 = strMeasure11,
    strMeasure12 = strMeasure12,
    strMeasure13 = strMeasure13,
    strMeasure14 = strMeasure14,
    strMeasure15 = strMeasure15,
    strMeasure16 = strMeasure16,
    strMeasure17 = strMeasure17,
    strMeasure18 = strMeasure18,
    strMeasure19 = strMeasure19,
    strMeasure20 = strMeasure20,
    strSource = strSource,
    strImageSource = strImageSource,
    strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
    dateModified = dateModified
)

fun MealDetailItemDTO.toMealDetail(): MealDetailItem = MealDetailItem(
    idMeal = idMeal,
    strMeal = strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags,
    strYoutube = strYoutube,
    strIngredient1 = strIngredient1,
    strIngredient2 = strIngredient2,
    strIngredient3 = strIngredient3,
    strIngredient4 = strIngredient4,
    strIngredient5 = strIngredient5,
    strIngredient6 = strIngredient6,
    strIngredient7 = strIngredient7,
    strIngredient8 = strIngredient8,
    strIngredient9 = strIngredient9,
    strIngredient10 = strIngredient10,
    strIngredient11 = strIngredient11,
    strIngredient12 = strIngredient12,
    strIngredient13 = strIngredient13,
    strIngredient14 = strIngredient14,
    strIngredient15 = strIngredient15,
    strIngredient16 = strIngredient16,
    strIngredient17 = strIngredient17,
    strIngredient18 = strIngredient18,
    strIngredient19 = strIngredient19,
    strIngredient20 = strIngredient20,
    strMeasure1 = strMeasure1,
    strMeasure2 = strMeasure2,
    strMeasure3 = strMeasure3,
    strMeasure4 = strMeasure4,
    strMeasure5 = strMeasure5,
    strMeasure6 = strMeasure6,
    strMeasure7 = strMeasure7,
    strMeasure8 = strMeasure8,
    strMeasure9 = strMeasure9,
    strMeasure10 = strMeasure10,
    strMeasure11 = strMeasure11,
    strMeasure12 = strMeasure12,
    strMeasure13 = strMeasure13,
    strMeasure14 = strMeasure14,
    strMeasure15 = strMeasure15,
    strMeasure16 = strMeasure16,
    strMeasure17 = strMeasure17,
    strMeasure18 = strMeasure18,
    strMeasure19 = strMeasure19,
    strMeasure20 = strMeasure20,
    strSource = strSource,
    strImageSource = strImageSource,
    strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
    dateModified = dateModified
)