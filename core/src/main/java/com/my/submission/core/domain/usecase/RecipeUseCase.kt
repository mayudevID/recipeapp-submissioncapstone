package com.my.submission.core.domain.usecase

import com.my.submission.core.data.Resource
import com.my.submission.core.domain.model.DetailRecipe
import com.my.submission.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
    fun getAllRecipe(): Flow<Resource<List<Recipe>>>
    fun getFavoriteRecipe(): Flow<List<Recipe>>
    fun setFavoriteRecipe(recipe: Recipe, state: Boolean)
    fun getDetailRecipe(key: String): Flow<Resource<DetailRecipe>>
}