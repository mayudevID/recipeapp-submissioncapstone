package com.my.submission.core.di

import androidx.room.Room
import com.my.submission.core.data.RecipeRepository
import com.my.submission.core.data.source.local.LocalDataSource
import com.my.submission.core.data.source.local.room.RecipeDatabase
import com.my.submission.core.data.source.remote.RemoteDataSource
import com.my.submission.core.data.source.remote.network.ApiService
import com.my.submission.core.domain.repository.IRecipeRepository
import com.my.submission.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<RecipeDatabase>().recipeDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            RecipeDatabase::class.java, "Recipe.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://masak-apa-tomorisakura.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRecipeRepository> {
        RecipeRepository(
            get(),
            get(),
            get()
        )
    }
}