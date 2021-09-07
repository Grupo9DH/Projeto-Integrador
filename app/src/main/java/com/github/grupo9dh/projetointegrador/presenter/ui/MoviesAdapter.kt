package com.github.grupo9dh.projetointegrador.presenter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.grupo9dh.projetointegrador.data.model.Movie
import com.github.grupo9dh.projetointegrador.databinding.ItemMoviesBinding


class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: ItemMoviesBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieOverview.text = movie.overview
            binding.tvMovieVoteAverage.text = movie.voteAverage.toString()
            binding.tvMovieReleaseDate.text = movie.releaseDate
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}