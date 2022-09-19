package com.example.movienow.API_SERVICE


import com.example.movienow.api_key.API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("movie/popular?api_key=$API_KEY&language=en-US&")
    fun loadMovies(

        @Query("page")
        page: Int,

        ): Single<API_Data>


}