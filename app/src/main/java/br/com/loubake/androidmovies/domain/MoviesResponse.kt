package br.com.loubake.androidmovies.domain

class MoviesResponse {

    enum class Status {
        SUCCESS,
        ERROR_TIMEOUT,
        ERROR_API
    }

    var status: Status? = null
    var listMovies: List<Movie>? = null
    var errorMessage: String? = null
}
