package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceMovies {

    @GET("movie/popular?api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY)
    suspend fun getMovies(): Response<MoviesDTO>
}
