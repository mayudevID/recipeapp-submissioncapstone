package com.my.submission.core.domain.usecase

import com.my.submission.core.domain.model.Recipe
import com.my.submission.core.domain.repository.IRecipeRepository

class RecipeInteractor(private val recipeRepository: IRecipeRepository): RecipeUseCase {

    override fun getAllRecipe() = recipeRepository.getAllRecipe()

    override fun getFavoriteRecipe() = recipeRepository.getFavoriteRecipe()

    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) = recipeRepository.setFavoriteRecipe(recipe, state)

    override fun getDetailRecipe(key: String) = recipeRepository.getDetailRecipe(key)
}