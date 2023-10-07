package br.com.loubake.androidmovies.data

import br.com.loubake.androidmovies.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val QUERY_STRING_API_KEY = "api_key=${BuildConfig.THE_MOVIE_DB_API_KEY}"
    }

    @GET("movie/popular?$QUERY_STRING_API_KEY")
    suspend fun getMovies(): Response<MoviesDTO>

    @GET("movie/{movieId}?$QUERY_STRING_API_KEY&append_to_response=images")
    suspend fun getMovieDetails(@Path("movieId") movieId: String): Response<MovieDTO>
}
