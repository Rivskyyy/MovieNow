package com.RivskyInc.movienow.API_SERVICE

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favourite_movies")
 data class Result(
//    @SerializedName("adult")
//    val adult: Boolean,
//    @SerializedName("backdrop_path")
//    val backdrop_path: String,
//    @SerializedName("genre_ids")
//    var genre_ids: List<Int>,
    @PrimaryKey
    @SerializedName("id")
    var id: Int?,
//    @SerializedName("original_language")
//    val original_language: String,
//    @SerializedName("original_title")
//    val original_title: String,
    @SerializedName("overview")
    var overview: String?,
//    @SerializedName("popularity")
//    val popularity: Double,
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("release_date")
    var release_date: String?,
    @SerializedName("title")
    var title: String?,
//    @SerializedName("video")
//    val video: Boolean,
    @SerializedName("vote_average")
    var vote_average: Double?,
//    @SerializedName("vote_count")
//    val vote_count: Int?


)

