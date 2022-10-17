package com.my.submission.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListRecipeResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: List<RecipeResponse?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable
