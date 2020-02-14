package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesResponse

class MovieService {

    fun getMovies(): MoviesResponse {
        val moviesFakeApi = mutableListOf<MoviesDTO>().apply {
            add(MoviesDTO("Movie 1", ""))
            add(MoviesDTO("Movie 2", ""))
            add(MoviesDTO("Movie 3", ""))
            add(MoviesDTO("Movie 4", ""))
            add(MoviesDTO("Movie 5", ""))
        }

        val movies = mutableListOf<Movie>()
        moviesFakeApi.map { movies.add(Movie(it.title, it.posterUrl)) }

        return MoviesResponse().apply {
            status = MoviesResponse.Status.SUCCESS
            listMovies = movies
        }
    }
}
