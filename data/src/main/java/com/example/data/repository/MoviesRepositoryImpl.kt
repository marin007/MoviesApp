package com.example.data.repository

import com.example.data.remote.MoviesRemoteApi
import com.example.domain.entity.Movie
import com.example.domain.entity.MovieResponseModel
import com.example.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(private val moviesRemoteApi: MoviesRemoteApi) : MoviesRepository {
    override suspend fun getPopularMovies(page: Int): MovieResponseModel = withContext(Dispatchers.IO) {
        moviesRemoteApi.getPopularMovies(page)
    }
}