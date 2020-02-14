package br.com.loubake.androidmovies.domain

interface MoviesRepository {

    fun getMoviesData() : MoviesResponse
}
