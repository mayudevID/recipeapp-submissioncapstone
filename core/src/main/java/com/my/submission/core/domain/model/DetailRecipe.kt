package com.my.submission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailRecipe(
    val method: String,
    val results: Results,
    val status: Boolean
) : Parcelable

@Parcelize
data class Author(
    val datePublished: String,
    val user: String,
) : Parcelable

@Parcelize
data class Results(
    val difficulty: String,
    val servings: String,
    val times: String,
    val ingredient: List<String?>,
    val thumb: String,
    val author: Author,
    val step: List<String?>,
    val title: String,
    val needItem: List<NeedItemItem?>,
    val desc: String
) : Parcelable

@Parcelize
data class NeedItemItem(
    val thumbItem: String,
    val itemName: String
) : Parcelable