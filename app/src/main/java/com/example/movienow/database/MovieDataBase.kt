package com.example.movienow.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase(){
    companion object{
        private var instance : MovieDataBase? = null
        private const val DB_NAME = "main.db"

        fun getInstance(context : Context) : MovieDataBase{

                if ( instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        MovieDataBase::class.java,
                        DB_NAME
                    ).build()
                }

            return instance!!
        }
    }
    abstract fun movieDao() : MovieDao
}