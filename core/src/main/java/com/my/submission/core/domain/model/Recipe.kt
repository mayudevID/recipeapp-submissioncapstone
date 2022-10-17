package com.my.submission.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val key: String,
    val thumb: String,
    val title: String,
    val times: String,
    val serving: String,
    val difficulty: String,
    var isFavorite: Boolean,
) : Parcelable