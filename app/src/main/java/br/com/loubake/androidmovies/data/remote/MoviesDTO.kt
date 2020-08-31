package br.com.loubake.androidmovies.data.remote

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("results")
    val results: List<MovieDTO>
)

data class MovieDTO(
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String
) {
    companion object {
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185"
    }

    fun getPosterUrl(): String {
        return POSTER_BASE_URL + posterPath
    }
}
