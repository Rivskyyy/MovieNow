package com.example.movienow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.database.MovieDao
import com.example.movienow.database.MovieDataBase

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao : MovieDao = MovieDataBase.getInstance(application).movieDao()

    fun getMovies() : LiveData<List<Result>>{
        return movieDao.getAllFavouriteMovie()
    }

}

