package com.example.movienow.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movienow.API_SERVICE.BASE_IMAGE_POSTER
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.nextInt


class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private var movies: List<Result> = ArrayList<Result>()
    fun setMovies(movies: List<Result>) {
        this.movies = movies
        notifyDataSetChanged()
        Collections.shuffle(movies)

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var posterImageView: ImageView
        private lateinit var nameOfMovie: TextView
        private lateinit var yearOfMovie : TextView
        private lateinit var descriptionOfMovie : TextView
        private lateinit var rating : TextView
        private lateinit var genre : TextView

        fun bindMovie(movie: Result) {

            posterImageView = itemView.findViewById(R.id.posterImage)
            nameOfMovie = itemView.findViewById(R.id.nameOfMovie)
            yearOfMovie = itemView.findViewById(R.id.yearOfMovie)
            descriptionOfMovie = itemView.findViewById(R.id.descriptionOfMovie)
            rating = itemView.findViewById(R.id.rating)
            genre = itemView.findViewById(R.id.genreTextView)

            genre.text = movie.genre_ids.toString()
            rating.text = movie.vote_average.toString()
            descriptionOfMovie.text = movie.overview
            yearOfMovie.text = movie.release_date
            nameOfMovie.text = movie.title
            Glide.with(itemView).load(BASE_IMAGE_POSTER + movie.poster_path).into(posterImageView)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return MyViewHolder(view)

    }



    override fun onBindViewHolder(holder: MyViewHolder,  position: Int) {
        holder.bindMovie(movies[position])


    }

    override fun getItemCount(): Int {
        return movies.size-19
        // Log.d("getItemCount", (movies.size).toString())
    }



}