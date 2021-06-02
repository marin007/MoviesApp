package com.example.moviesapp.ui.movies.movieinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.data.BuildConfig
import com.example.moviesapp.R
import com.example.moviesapp.common.BaseFragment
import com.example.moviesapp.databinding.FragmentMovieInfoBinding
import com.example.moviesapp.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class MovieInfoFragment : BaseFragment<FragmentMovieInfoBinding, MoviesViewModel>() {
    override fun viewModel(): MoviesViewModel = getSharedViewModel()

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieInfoBinding {
        return FragmentMovieInfoBinding.inflate(inflater, container, false)
    }

    override fun subscribeToUiChanges() {
    }

    override fun subscribeToEvents() {
    }

    override fun prepareUi() {
        val actionbar = (activity as AppCompatActivity?)?.supportActionBar
        actionbar?.title = getString(R.string.movie_info)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        binding.textViewOverView.text = viewModel.selectedМovie.overview
        viewModel.selectedМovie.poster_path?.let {
            val imageURLFull = "${BuildConfig.IMAGES_URL}${viewModel.selectedМovie.poster_path}"
            Glide.with(binding.imageViewFullPoster).load(imageURLFull)
                .into(binding.imageViewFullPoster)
        }
    }

}