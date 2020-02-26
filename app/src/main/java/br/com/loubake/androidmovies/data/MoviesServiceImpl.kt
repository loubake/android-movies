package br.com.loubake.androidmovies.data

import retrofit2.Response
import retrofit2.Retrofit

class MoviesServiceImpl(
    val retrofit: Retrofit
) : MoviesService {

    override suspend fun getMovies(): Response<MoviesDTO> {
        val moviesService = retrofit.create(MoviesService::class.java)
        return moviesService.getMovies()
    }
}
