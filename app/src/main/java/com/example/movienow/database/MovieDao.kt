package com.example.movienow.database

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movienow.API_SERVICE.Result

@Dao
interface MovieDao {

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovie(): LiveData<List<Result>>

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId ")
    fun getFavouriteById(movieId: Int): LiveData<Result>

    @Insert
    fun insertMovie(movie: Result)


    @Query("DELETE FROM favourite_movies WHERE id = :movieId ")
    fun removeMovie(movieId: Int)


}
