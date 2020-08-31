package br.com.loubake.androidmovies.data.remote

import br.com.loubake.androidmovies.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val GET_MOVIES_PATH = "movie/popular"
        const val GET_MOVIES_QUERY_STRING = "?api_key="
    }

    @GET("$GET_MOVIES_PATH$GET_MOVIES_QUERY_STRING${BuildConfig.THE_MOVIE_DB_API_KEY}")
    suspend fun getMovies(): Response<MoviesDTO>
}
