package com.example.movienow.API_SERVICE


import com.example.movienow.api_key.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("discover/movie?api_key=$API_KEY&vote_average.gte=${5.0}&vote_average.lte=${10.0}")
    fun loadMovies(

        @Query("page")
        page: Int,
        @Query("language")
        language: String,
        @Query("region")
        region: String,
        @Query("vote_count.gte")
        vote_countMin: Int,
        @Query("vote_count.lte")
        vote_countMax: Int,
//        @Query("with_genres")
//        genre : Int
    ): Single<API_Data>


}