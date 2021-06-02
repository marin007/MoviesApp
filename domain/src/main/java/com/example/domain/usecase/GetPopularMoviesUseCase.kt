package com.example.domain.usecase

import com.example.domain.entity.MovieResponseModel
import com.example.domain.repository.MoviesRepository

class GetPopularMoviesUseCase (
    private val moviesRepository: MoviesRepository
        ) {
    suspend fun getPopularMovies(page: Int): MovieResponseModel {
        return moviesRepository.getPopularMovies(page = page)
    }
}