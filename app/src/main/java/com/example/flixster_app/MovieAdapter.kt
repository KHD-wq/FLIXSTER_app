package com.example.flixster_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        private val movieDescription: TextView = itemView.findViewById(R.id.movie_overview)
        private val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bind(movie: Movie) {
            movieTitle.text = movie.title
            movieDescription.text = movie.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(moviePoster)
        }
    }
}
