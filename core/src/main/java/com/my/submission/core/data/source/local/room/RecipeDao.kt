package com.my.submission.core.data.source.local.room

import androidx.room.*
import com.my.submission.core.data.source.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAllRecipe(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe where isFavorite = 1")
    fun getFavoriteRecipe(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: List<RecipeEntity>)

    @Update
    fun updateFavoriteRecipe(recipe: RecipeEntity)
}
