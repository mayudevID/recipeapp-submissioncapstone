package com.my.submission.core.data.source.remote

import android.util.Log
import com.my.submission.core.data.source.remote.network.ApiResponse
import com.my.submission.core.data.source.remote.network.ApiService
import com.my.submission.core.data.source.remote.response.DetailRecipeResponse
import com.my.submission.core.data.source.remote.response.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllRecipe(): Flow<ApiResponse<List<RecipeResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.results as List<RecipeResponse>
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailRecipe(key: String): Flow<ApiResponse<DetailRecipeResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getDetailRecipe(key)
                if (response.status == true){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

