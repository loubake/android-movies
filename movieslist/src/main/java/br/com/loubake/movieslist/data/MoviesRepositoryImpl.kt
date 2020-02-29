package br.com.loubake.movieslist.data

import br.com.loubake.movieslist.domain.Movie
import br.com.loubake.movieslist.domain.MoviesRepository
import br.com.loubake.movieslist.domain.MoviesResponse

class MoviesRepositoryImpl(val service: MoviesService): MoviesRepository {

    override suspend fun getMoviesData(apiKey: String) : MoviesResponse {
        val moviesResponse = MoviesResponse()
        val response = service.getMovies(apiKey)

        if (response.isSuccessful) {
            moviesResponse.status = MoviesResponse.Status.SUCCESS
            val movies = mutableListOf<Movie>()
            response.body()?.results?.map {
                movies.add(Movie(it.title, it.getPosterUrl()))
            }
            moviesResponse.listMovies = movies
        } else {
            moviesResponse.status = MoviesResponse.Status.ERROR_API
        }

        return moviesResponse
    }
}
