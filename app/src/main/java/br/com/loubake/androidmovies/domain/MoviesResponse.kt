package br.com.loubake.androidmovies.domain

class MoviesResponse {

    lateinit var status: ResponseStatus
    var listMovies: List<Movie>? = null
    var errorMessage: String? = null
    var errorCode: Int? = null
}
