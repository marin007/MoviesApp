package com.example.moviesapp.ui.movies.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.BuildConfig
import com.example.domain.entity.Movie
import com.example.moviesapp.R

class MoviesListAdapter(
    private val action: (movie: Movie?) -> Unit
) : PagingDataAdapter<Movie, MoviesListAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text: String? = getItem(position)?.title
        val rating: String = getItem(position)?.vote_average.toString()
        getItem(position)?.let {
            val imageURLFull = "${BuildConfig.IMAGES_URL}${getItem(position)?.poster_path}"
            Glide.with(holder.imageViewPoster)
                .load(imageURLFull)
                .thumbnail(0.25f)
                .into(holder.imageViewPoster)
        }

        holder.textViewTitle.text = text

        holder.textViewRating.text = rating

        holder.mainRowWrapper.setOnClickListener {
            action(getItem(position))
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mainRowWrapper: ConstraintLayout =
            view.findViewById(R.id.constraintLayoutRowWrapper)

        val textViewTitle: TextView =
            view.findViewById(R.id.textViewTitle)

        val textViewRating: TextView =
            view.findViewById(R.id.textViewRating)

        val imageViewPoster: ImageView =
            view.findViewById(R.id.imageViewPoster)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.title == newItem.title
        }
    }
}