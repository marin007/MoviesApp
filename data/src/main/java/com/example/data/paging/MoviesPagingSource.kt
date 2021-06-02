package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.MoviesRemoteApi
import com.example.domain.entity.Movie
import com.example.domain.usecase.GetPopularMoviesUseCase

class MoviesPagingSource(private val useCaseGetPopularMoviesUseCase: GetPopularMoviesUseCase) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page: Int = params.key ?: FIRST_PAGE_INDEX
            val response = useCaseGetPopularMoviesUseCase.getPopularMovies(page)
            LoadResult.Page(
                response.results, prevKey = if (page == FIRST_PAGE_INDEX) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}