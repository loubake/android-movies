package br.com.loubake.androidmovies.data

import retrofit2.Response

class MoviesService {

    suspend fun getMovies(): Response<MoviesDTO> {
        return ApiClient().getMovies()
    }
}
