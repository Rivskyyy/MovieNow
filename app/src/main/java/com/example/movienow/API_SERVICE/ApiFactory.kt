package com.RivskyInc.movienow.API_SERVICE

import com.RivskyInc.movienow.API_SERVICE.API
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL: String = "https://api.themoviedb.org/3/"
const val BASE_IMAGE_POSTER: String = "https://image.tmdb.org/t/p/w500/"


class ApiFactory {

    companion object {

        val retrofitFactory = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val apiService = retrofitFactory.create(API::class.java)

    }

}
