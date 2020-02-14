package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.domain.MoviesRepository
import br.com.loubake.androidmovies.domain.MoviesResponse

class MoviesRepositoryImpl(val service: MovieService): MoviesRepository {

    override fun getMoviesData() : MoviesResponse {
        return service.getMovies()
    }
}
