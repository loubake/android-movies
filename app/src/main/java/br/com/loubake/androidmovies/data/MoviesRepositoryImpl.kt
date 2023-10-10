package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MovieDetailsResponse
import br.com.loubake.androidmovies.domain.MoviesRepository
import br.com.loubake.androidmovies.domain.MoviesResponse
import br.com.loubake.androidmovies.domain.ResponseStatus

class MoviesRepositoryImpl(val service: MoviesService): MoviesRepository {

    override suspend fun getMoviesData() : MoviesResponse {
        val moviesResponse = MoviesResponse()
        val response = service.getMovies()

        if (response.isSuccessful) {
            moviesResponse.status = ResponseStatus.SUCCESS
            val movies = mutableListOf<Movie>()
            response.body()?.results?.map {
                movies.add(
                    Movie(
                        id = it.id,
                        title = it.title,
                        description = it.overview,
                        releaseDate = it.releaseDate,
                        genres = it.getGenresNames(),
                        tagline = it.tagline,
                        posterUrl = it.getPosterUrl(),
                        backdropUrl = it.getBackdropUrl(),
                    )
                )
            }
            moviesResponse.listMovies = movies
        } else {
            moviesResponse.status = ResponseStatus.ERROR_API
        }

        return moviesResponse
    }

    override suspend fun getMovieDetailsData(movieId: String): MovieDetailsResponse {
        val response = service.getMovieDetails(movieId = movieId)
        val movieDetailsResponse = MovieDetailsResponse()

        if (response.isSuccessful) {
            movieDetailsResponse.status = ResponseStatus.SUCCESS
            response.body()?.let { body ->
                movieDetailsResponse.movie = Movie(
                    id = body.id,
                    title = body.title,
                    description = body.overview,
                    releaseDate = body.releaseDate,
                    genres = body.getGenresNames(),
                    tagline = body.tagline,
                    posterUrl = body.getPosterUrl(),
                    backdropUrl = body.getBackdropUrl(),
                    backdropImages = body.getBackdropUrlsList(),
                    posterImages = body.getPosterUrlsList()
                )
            }
        } else {
            movieDetailsResponse.status = ResponseStatus.ERROR_API
        }

        return movieDetailsResponse
    }
}
