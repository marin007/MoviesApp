package com.example.moviesapp.ui.movies

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.data.paging.MoviesPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MoviesViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {

    lateinit var selectedМovie: Movie

    val movies: Flow<PagingData<Movie>> = Pager (config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviesPagingSource(getPopularMoviesUseCase) }).flow.cachedIn(viewModelScope).flowOn(Dispatchers.IO)

}