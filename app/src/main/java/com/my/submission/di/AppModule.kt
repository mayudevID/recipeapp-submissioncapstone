package com.my.submission.di

import com.my.submission.core.domain.usecase.RecipeInteractor
import com.my.submission.core.domain.usecase.RecipeUseCase
import com.my.submission.detail.DetailRecipeViewModel
import com.my.submission.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RecipeUseCase> { RecipeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailRecipeViewModel(get()) }
}