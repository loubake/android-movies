package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesRepository

class MoviesRepositoryImpl(val service: MovieService): MoviesRepository {

    override fun getMoviesData() : List<Movie> {
        return service.getMovies()
    }
}
