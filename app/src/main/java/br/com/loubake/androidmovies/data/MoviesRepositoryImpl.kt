package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.data.local.MoviesDao
import br.com.loubake.androidmovies.data.remote.MovieDTO
import br.com.loubake.androidmovies.data.remote.MoviesService
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesRepository
import br.com.loubake.androidmovies.domain.MoviesResponse
import br.com.loubake.androidmovies.domain.ResponseStatus

class MoviesRepositoryImpl(val service: MoviesService, val moviesDao: MoviesDao) :
    MoviesRepository {

    private var moviesResponse = MoviesResponse()

    private suspend fun insertMoviesInLocalDatabase(movies: List<Movie>) {
        moviesDao.insertMovies(movies)
    }

    override suspend fun getMoviesData(): MoviesResponse {
        val moviesFromDB = moviesDao.getMovies()

        if (moviesFromDB.isNullOrEmpty()) {
            moviesResponse = fetchFromRemote()

            moviesResponse.listMovies?.let {
                insertMoviesInLocalDatabase(it)
            }
        } else {
            moviesResponse.status = ResponseStatus.SUCCESS
            moviesResponse.listMovies = moviesFromDB
        }

        return moviesResponse
    }

    private suspend fun fetchFromRemote(): MoviesResponse {
        val response = service.getMovies()

        if (response.isSuccessful) {
            moviesResponse.status = ResponseStatus.SUCCESS
            moviesResponse.listMovies = moviesMapper(response.body()?.results)
        } else {
            moviesResponse.status = ResponseStatus.ERROR_API
            moviesResponse.errorCode = response.code()
        }

        return moviesResponse
    }

    private fun moviesMapper(moviesDTO: List<MovieDTO>?): List<Movie>? {
        return moviesDTO?.map {
            Movie(it.title, it.getPosterUrl())
        }
    }
}
