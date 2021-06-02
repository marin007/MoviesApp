package com.example.moviesapp.di

import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesapp.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel {
        MoviesViewModel(get())
    }
}

val useCasesModule = module {
    factory {
        GetPopularMoviesUseCase(
            moviesRepository = get()
        )
    }
}