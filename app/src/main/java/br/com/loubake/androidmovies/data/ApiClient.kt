package br.com.loubake.androidmovies.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private val retrofit = getRetrofit()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getMovies(): Response<MoviesDTO> {
        val moviesService = retrofit.create(ApiServiceMovies::class.java)
        return moviesService.getMovies()
    }
}
