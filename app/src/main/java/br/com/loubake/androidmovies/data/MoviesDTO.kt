package br.com.loubake.androidmovies.data

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("results")
    val results: List<MovieDTO>
)

data class MovieDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterUrl: String
)
