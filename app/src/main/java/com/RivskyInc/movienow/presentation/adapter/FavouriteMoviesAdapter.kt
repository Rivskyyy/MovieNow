package com.RivskyInc.movienow.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.RivskyInc.movienow.R
import com.bumptech.glide.Glide
import com.RivskyInc.movienow.API_SERVICE.BASE_IMAGE_POSTER
import com.RivskyInc.movienow.API_SERVICE.Result
import java.util.*

class FavouriteMoviesAdapter(): RecyclerView.Adapter<FavouriteMoviesAdapter.MyViewHolder>(){


    private var movies: List<Result> = ArrayList<Result>()


    var onMovieClickListener_object: onMovieClickListener? = null



    fun setMovies(movies: List<Result>){
        this.movies = movies
        notifyDataSetChanged()

    }
    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var posterImageView: ImageView
        private lateinit var nameOfMovie: TextView
        //        private lateinit var yearOfMovie: TextView
//        private lateinit var descriptionOfMovie: TextView
       // private lateinit var rating: TextView


        fun bindMovie(movie: Result) {

            posterImageView = itemView.findViewById(R.id.posterImage)
            nameOfMovie = itemView.findViewById(R.id.nameOfMovie)
//            yearOfMovie = itemView.findViewById(R.id.yearOfMovie)
//            descriptionOfMovie = itemView.findViewById(R.id.descriptionOfMovie)
         //   rating = itemView.findViewById(R.id.rating)
//            imageFavorite = itemView.findViewById(R.id.imageButtonFavor)

        //    rating.text = movie.vote_average.toString()
//            descriptionOfMovie.text = movie.overview
//            yearOfMovie.text = movie.release_date
            nameOfMovie.text = movie.title

            Glide.with(itemView).load(BASE_IMAGE_POSTER + movie.poster_path).into(posterImageView)
        }

    }




    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindMovie(movies[position])
        val movie: Result = movies.get(position)

        holder.itemView.setOnClickListener {

            onMovieClickListener_object?.onMovieClick(movie)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_movie_items, parent, false)
        return MyViewHolder(view)
    }

    interface onMovieClickListener {
        fun onMovieClick(movie: Result)
    }
}