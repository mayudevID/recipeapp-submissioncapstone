package com.my.submission.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity (
    @PrimaryKey
    val key: String,
    val thumb: String,
    val title: String,
    val times: String,
    val serving: String,
    val difficulty: String,
    var isFavorite: Boolean = false
)