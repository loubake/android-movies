package br.com.loubake.movieslist.domain

interface MoviesRepository {

    suspend fun getMoviesData(apiKey: String) : MoviesResponse
}
