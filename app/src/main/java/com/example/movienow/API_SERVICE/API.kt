package com.RivskyInc.movienow.API_SERVICE


import com.RivskyInc.movienow.api_key.API_KEY
import com.RivskyInc.movienow.api_key.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("discover/movie?api_key=$API_KEY&sort_by=popularity.desc&")
    fun loadMovies(

        @Query("page")
        page: Int?,
        @Query("language")
        language: String,
//        @Query("region")
//        region: String,
        @Query("vote_count.gte")
        vote_countMin: Int,
        @Query("vote_count.lte")
        vote_countMax: Int,
        @Query("with_genres")
        genre : Int?,
        @Query("vote_average.gte")
        voteAverageMin : Int?,
        @Query("vote_average.lte")
        voteAverageMax : Int?,


    ): Single<API_Data>


}