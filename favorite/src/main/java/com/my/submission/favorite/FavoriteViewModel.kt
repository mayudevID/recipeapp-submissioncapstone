package com.my.submission.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.my.submission.core.domain.usecase.RecipeUseCase

class FavoriteViewModel(recipeUseCase: RecipeUseCase) : ViewModel() {
    val favoriteRecipe = recipeUseCase.getFavoriteRecipe().asLiveData()
}

