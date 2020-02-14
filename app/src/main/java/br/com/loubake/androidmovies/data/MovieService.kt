package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.domain.Movie

class MovieService {

    fun getMovies(): List<Movie> {
        val moviesFakeApi = mutableListOf<MovieResponse>().apply {
            add(MovieResponse("Movie 1", ""))
            add(MovieResponse("Movie 2", ""))
            add(MovieResponse("Movie 3", ""))
            add(MovieResponse("Movie 4", ""))
            add(MovieResponse("Movie 5", ""))
        }

        val movies = mutableListOf<Movie>()
        moviesFakeApi.map { movies.add(Movie(it.title, it.posterUrl)) }

        return movies
    }
}
