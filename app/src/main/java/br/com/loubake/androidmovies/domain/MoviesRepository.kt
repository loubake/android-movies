package br.com.loubake.androidmovies.domain

interface MoviesRepository {

    suspend fun getMoviesData() : MoviesResponse
}
