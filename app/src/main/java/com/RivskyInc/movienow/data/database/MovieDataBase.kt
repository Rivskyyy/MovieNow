package com.RivskyInc.movienow.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.RivskyInc.movienow.API_SERVICE.Result

@Database(entities = [Result::class], version = 7, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    companion object {
        private var instance: MovieDataBase? = null
        private const val DB_NAME = "main.db"


        fun getInstance(applicationContext: Context): MovieDataBase {

//                db?.let { return it  }
//                   val instance = Room.databaseBuilder(context, MovieDataBase::class.java,DB_NAME).build()
//            db = instance
//            Log.d("db", "db is created")
//            return instance
            if (instance == null) {
                instance = Room.databaseBuilder(applicationContext,
                    MovieDataBase::class.java,
                    DB_NAME,
                ).fallbackToDestructiveMigration().build()
                if ( instance != null ) {

                    Log.d("instance", "instance is succed   " )
                } else {
                    Log.d("instance", "instance is failed ")
                }


            }
            return instance!!

        }

    }

    abstract fun movieDao(): MovieDao


}