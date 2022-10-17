package com.my.submission.core.data

import com.my.submission.core.data.source.local.LocalDataSource
import com.my.submission.core.data.source.remote.RemoteDataSource
import com.my.submission.core.data.source.remote.network.ApiResponse
import com.my.submission.core.data.source.remote.response.RecipeResponse
import com.my.submission.core.domain.model.DetailRecipe
import com.my.submission.core.domain.model.Recipe
import com.my.submission.core.domain.repository.IRecipeRepository
import com.my.submission.core.utils.AppExecutors
import com.my.submission.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class RecipeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRepository {

    override fun getAllRecipe(): Flow<Resource<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<RecipeResponse>>() {
            override fun loadFromDB(): Flow<List<Recipe>> {
                return localDataSource.getAllRecipe().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Recipe>?): Boolean =
//                data == null || data.isEmpty()
                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<RecipeResponse>>> =
                remoteDataSource.getAllRecipe()

            override suspend fun saveCallResult(data: List<RecipeResponse>) {
                val recipeList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertRecipe(recipeList)
            }
        }.asFlow()

    override fun getFavoriteRecipe(): Flow<List<Recipe>> {
        return localDataSource.getFavoriteRecipe().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) {
        val recipeEntity = DataMapper.mapDomainToEntity(recipe)
        appExecutors.diskIO().execute { localDataSource.setFavoriteRecipe(recipeEntity, state) }
    }

    override fun getDetailRecipe(key: String): Flow<Resource<DetailRecipe>> {
        return flow {
            emit(Resource.Loading())
            val result = remoteDataSource.getDetailRecipe(key)
            when (val data = result.first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapResponsesToDomain(data.data)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(data.errorMessage))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error("Data kosong"))
                }
            }
        }
    }


}

