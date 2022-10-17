package com.my.submission.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailRecipeResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val results: ResultsResponse? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class AuthorResponse(

	@field:SerializedName("datePublished")
	val datePublished: String? = null,

	@field:SerializedName("user")
	val user: String? = null
) : Parcelable

@Parcelize
data class ResultsResponse(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("servings")
	val servings: String? = null,

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("ingredient")
	val ingredient: List<String?>? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("author")
	val author: AuthorResponse? = null,

	@field:SerializedName("step")
	val step: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("needItem")
	val needItem: List<NeedItemItemResponse?>? = null,

	@field:SerializedName("desc")
	val desc: String? = null
) : Parcelable

@Parcelize
data class NeedItemItemResponse(

	@field:SerializedName("thumb_item")
	val thumbItem: String? = null,

	@field:SerializedName("item_name")
	val itemName: String? = null
) : Parcelable
