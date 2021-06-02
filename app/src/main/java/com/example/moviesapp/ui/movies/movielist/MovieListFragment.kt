package com.example.moviesapp.ui.movies.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entity.Movie
import com.example.moviesapp.R
import com.example.moviesapp.common.BaseFragment
import com.example.moviesapp.databinding.MovieListFragmentBinding
import com.example.moviesapp.ui.movies.MoviesViewModel
import com.example.moviesapp.ui.utils.showErrorToast
import com.example.moviesapp.ui.utils.verifyAvailableNetwork
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class MovieListFragment : BaseFragment<MovieListFragmentBinding, MoviesViewModel>() {

    val recyclerViewAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(::selectMovie)
    }

    override fun viewModel(): MoviesViewModel = getSharedViewModel()

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MovieListFragmentBinding {
        return MovieListFragmentBinding.inflate(inflater, container, false)
    }

    override fun subscribeToUiChanges() {

    }

    override fun subscribeToEvents() {
    }

    override fun onResume() {
        super.onResume()
        if (verifyAvailableNetwork(secureContext)) {
            fetchMovies()
        } else if (recyclerViewAdapter.itemCount == 0) {
            showErrorToast(secureContext, getString(R.string.no_internet_error))
        }
    }


    override fun prepareUi() {
        val actionbar = (activity as AppCompatActivity?)?.supportActionBar
        actionbar?.title = getString(R.string.app_name)
        actionbar?.setDisplayHomeAsUpEnabled(false)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(secureContext, 2)
            adapter = recyclerViewAdapter
            recyclerViewAdapter.addLoadStateListener { loadState ->
                if (!verifyAvailableNetwork(secureContext) && (loadState.refresh is LoadState.Error
                            || loadState.append is LoadState.Error
                            || loadState.prepend is LoadState.Error)
                ) {
                    showErrorToast(secureContext, getString(R.string.no_internet_error))
                }
            }
        }
    }

    private fun fetchMovies() {
        lifecycleScope.launchWhenCreated {
            viewModel.movies.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    private fun selectMovie(movie: Movie?) {
        movie?.let {
            viewModel.selectedМovie = movie
            moveToMovieInfoScreen()
        }
    }

    private fun moveToMovieInfoScreen() {
        findNavController().navigate(R.id.action_movieListFragment_to_movieInfoFragment)
    }
}