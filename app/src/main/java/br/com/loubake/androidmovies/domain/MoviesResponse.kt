package br.com.loubake.androidmovies.domain

class MoviesResponse {
    var status: ResponseStatus? = null
    var listMovies: List<Movie>? = null
    var errorMessage: String? = null
}
