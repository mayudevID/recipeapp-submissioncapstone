package com.my.submission.core.utils

import com.my.submission.core.data.source.local.entity.RecipeEntity
import com.my.submission.core.data.source.remote.response.*
import com.my.submission.core.domain.model.*

object DataMapper {
    fun mapResponsesToEntities(input: List<RecipeResponse>): List<RecipeEntity> {
        val recipeList = ArrayList<RecipeEntity>()
        input.map {
            val recipe = RecipeEntity(
                key = it.key,
                thumb = it.thumb,
                title = it.title,
                times = it.times,
                serving = it.serving,
                difficulty = it.difficulty,
                isFavorite = false
            )
            recipeList.add(recipe)
        }
        return recipeList
    }

    fun mapEntitiesToDomain(input: List<RecipeEntity>): List<Recipe> =
        input.map {
            Recipe(
                key = it.key,
                thumb = it.thumb,
                title = it.title,
                times = it.times,
                serving = it.serving,
                difficulty = it.difficulty,
                isFavorite = it.isFavorite,
            )
        }

    fun mapDomainToEntity(input: Recipe) = RecipeEntity(
        key = input.key,
        thumb = input.thumb,
        title = input.title,
        times = input.times,
        serving = input.serving,
        difficulty = input.difficulty,
        isFavorite = input.isFavorite
    )

    fun mapResponsesToDomain(input: DetailRecipeResponse) = DetailRecipe(
        method = input.method ?: "NULL",
        status = input.status ?: false,
        results = resultsConvert(input.results!!),

    )

    fun resultsConvert(input: ResultsResponse) = Results(
        times = input.times ?: "NULL",
        difficulty = input.difficulty ?: "NULL",
        servings = input.servings ?: "NULL",
        ingredient = listStringConvert(input.ingredient!!),
        thumb = input.thumb ?: "NULL",
        author = authorConvert(input.author!!),
        step = listStringConvert(input.step!!),
        title = input.title ?: "NULL",
        needItem = needItemItemConvert(input.needItem!!),
        desc = input.desc ?: "NULL",
    )

    fun authorConvert(input: AuthorResponse) =  Author(
        datePublished = input.datePublished ?: "NULL",
        user = input.user ?: "NULL",
    )

    fun needItemItemConvert(input: List<NeedItemItemResponse?>): List<NeedItemItem> {
        val data = ArrayList<NeedItemItem>()
        input.map {
            val dataItem = NeedItemItem(
                thumbItem = it?.thumbItem ?: "NULL",
                itemName = it?.itemName ?: "NULL"
            )
            data.add(dataItem)
        }
        return data.toList()
    }

    fun listStringConvert(input: List<String?>) : List<String> {
        val data = ArrayList<String>()
        input.map {
            data.add(it ?: "No data")
        }
        return data.toList()
    }
}