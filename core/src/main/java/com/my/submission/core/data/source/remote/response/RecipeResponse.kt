package com.my.submission.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeResponse(

    @field:SerializedName("difficulty")
    val difficulty: String,

    @field:SerializedName("times")
    val times: String,

    @field:SerializedName("thumb")
    val thumb: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("key")
    val key: String,

    @field:SerializedName("serving")
    val serving: String,
) : Parcelable