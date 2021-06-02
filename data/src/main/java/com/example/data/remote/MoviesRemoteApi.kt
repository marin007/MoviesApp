package com.example.data.remote

import com.example.domain.entity.MovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesRemoteApi {
    @GET("top_rated?language=en-US")
    suspend fun getPopularMovies(@Query("page") query: Int): MovieResponseModel
}