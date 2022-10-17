package com.my.submission.core.data.source.remote.network

import com.my.submission.core.data.source.remote.response.DetailRecipeResponse
import com.my.submission.core.data.source.remote.response.ListRecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/recipes")
    suspend fun getList(): ListRecipeResponse

    @GET("api/recipe/{key}")
    suspend fun getDetailRecipe(@Path("key") key: String): DetailRecipeResponse
}
