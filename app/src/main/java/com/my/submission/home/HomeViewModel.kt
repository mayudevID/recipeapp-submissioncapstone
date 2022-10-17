package com.my.submission.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.my.submission.core.domain.usecase.RecipeUseCase

class HomeViewModel(recipeUseCase: RecipeUseCase) : ViewModel() {
    val recipe = recipeUseCase.getAllRecipe().asLiveData()
}

