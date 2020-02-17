package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesRepository
import br.com.loubake.androidmovies.domain.MoviesResponse

class MoviesRepositoryImpl(val service: MoviesService): MoviesRepository {

    override suspend fun getMoviesData() : MoviesResponse {
        val moviesResponse = MoviesResponse()
        val response = service.getMovies()

        if (response.isSuccessful) {
            moviesResponse.status = MoviesResponse.Status.SUCCESS
            val movies = mutableListOf<Movie>()
            response.body()?.results?.map {
                movies.add(Movie(it.title, it.posterUrl))
            }
            moviesResponse.listMovies = movies
        } else {
            moviesResponse.status = MoviesResponse.Status.ERROR_API
        }

        return moviesResponse
    }
}
