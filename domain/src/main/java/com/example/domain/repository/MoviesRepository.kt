package com.example.domain.repository

import com.example.domain.entity.MovieResponseModel

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): MovieResponseModel

}