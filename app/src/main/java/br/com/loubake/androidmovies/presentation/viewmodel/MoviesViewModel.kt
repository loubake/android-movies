package br.com.loubake.androidmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.loubake.androidmovies.data.MovieService
import br.com.loubake.androidmovies.data.MoviesRepositoryImpl
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesResponse

class MoviesViewModel: ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Movie>>()
    val notifyRequestFinishedLiveData = MutableLiveData<Unit>()

    private val moviesService = MovieService()
    private val moviesRepository = MoviesRepositoryImpl(moviesService)
    private var getMoviesUseCase = GetMoviesUseCase(moviesRepository)

    fun getMovies() {
        val result = getMoviesUseCase()

        when (result.status) {
            MoviesResponse.Status.SUCCESS -> {
                notifyRequestFinishedLiveData.postValue(Unit)
                moviesListLiveData.postValue(result.listMovies)
            }
            MoviesResponse.Status.ERROR_API -> {
                notifyRequestFinishedLiveData.postValue(Unit)
            }
        }
    }
}
