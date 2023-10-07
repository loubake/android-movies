package br.com.loubake.androidmovies.domain

interface MoviesRepository {

    suspend fun getMoviesData() : MoviesResponse
    suspend fun getMovieDetailsData(movieId: String) : MovieDetailsResponse
}
