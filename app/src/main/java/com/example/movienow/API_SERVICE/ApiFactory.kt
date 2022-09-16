package com.example.movienow.API_SERVICE

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Arrays.toString
import java.util.Objects.toString


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
