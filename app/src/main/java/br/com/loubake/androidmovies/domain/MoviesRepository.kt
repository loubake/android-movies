package br.com.loubake.androidmovies.domain

interface MoviesRepository {

    fun getMoviesData() : List<Movie>
}
