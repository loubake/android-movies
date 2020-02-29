package br.com.loubake.movieslist.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val GET_MOVIES_PATH = "movie/popular"
        const val GET_MOVIES_QUERY_STRING = "api_key"
    }

    @GET("$GET_MOVIES_PATH")
    suspend fun getMovies(@Query("$GET_MOVIES_QUERY_STRING") apiKey: String): Response<MoviesDTO>
}
