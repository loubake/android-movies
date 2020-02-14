package br.com.loubake.androidmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.loubake.androidmovies.data.MovieService
import br.com.loubake.androidmovies.data.MoviesRepositoryImpl
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.Movie

class MoviesViewModel: ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Movie>>()
    val notifyRequestFinishedWithErrorLiveData = MutableLiveData<Unit>()

    private val moviesService = MovieService()
    private val moviesRepository = MoviesRepositoryImpl(moviesService)
    private var getMoviesUseCase = GetMoviesUseCase(moviesRepository)

    fun getMovies() {
        val result = getMoviesUseCase()

        moviesListLiveData.postValue(result)
    }
}
