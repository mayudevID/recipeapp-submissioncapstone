package com.my.submission.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.my.submission.core.domain.model.Recipe
import com.my.submission.core.domain.usecase.RecipeUseCase

class DetailRecipeViewModel(private val recipeUseCase: RecipeUseCase) : ViewModel() {
    fun setFavoriteRecipe(recipe: Recipe, newStatus:Boolean) =
        recipeUseCase.setFavoriteRecipe(recipe, newStatus)
    fun getDetailRecipe(key: String) = recipeUseCase.getDetailRecipe(key).asLiveData()
}

