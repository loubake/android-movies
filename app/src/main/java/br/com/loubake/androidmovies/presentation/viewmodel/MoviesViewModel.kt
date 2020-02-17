package br.com.loubake.androidmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.loubake.androidmovies.data.MoviesService
import br.com.loubake.androidmovies.data.MoviesRepositoryImpl
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Movie>>()
    val notifyRequestFinishedLiveData = MutableLiveData<Unit>()

    private val moviesService = MoviesService()
    private val moviesRepository = MoviesRepositoryImpl(moviesService)
    private var getMoviesUseCase = GetMoviesUseCase(moviesRepository)

    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
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
}
