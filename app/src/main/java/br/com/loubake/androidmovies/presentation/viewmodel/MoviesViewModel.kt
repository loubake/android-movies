package br.com.loubake.androidmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesResponse
import br.com.loubake.androidmovies.domain.ResponseStatus
import kotlinx.coroutines.launch

class MoviesViewModel(
    val useCaseGetMovies: GetMoviesUseCase
) : ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Movie>>()
    val notifyRequestFinishedLiveData = MutableLiveData<Unit>()
    val errorLiveData = MutableLiveData<Unit>()

    fun getMovies() {
        viewModelScope.launch {
            val result = useCaseGetMovies()
            notifyRequestFinishedLiveData.postValue(Unit)

            when (result.status) {
                ResponseStatus.SUCCESS -> {
                    moviesListLiveData.postValue(result.listMovies)
                }
                ResponseStatus.ERROR_API, ResponseStatus.ERROR_TIMEOUT -> {
                    handleError(result)
                }
            }
        }
    }

    fun handleError(moviesResponse: MoviesResponse) {
        errorLiveData.postValue(Unit)
    }
}
