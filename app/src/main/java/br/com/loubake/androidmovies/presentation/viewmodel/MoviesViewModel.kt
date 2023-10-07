package br.com.loubake.androidmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.MoviesResponse
import br.com.loubake.androidmovies.domain.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    val useCaseGetMovies: GetMoviesUseCase
) : ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Movie>>()
    val notifyRequestFinishedLiveData = MutableLiveData<Unit>()

    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = useCaseGetMovies()

            when (result.status) {
                ResponseStatus.SUCCESS -> {
                    notifyRequestFinishedLiveData.postValue(Unit)
                    moviesListLiveData.postValue(result.listMovies)
                }
                else -> {
                    notifyRequestFinishedLiveData.postValue(Unit)
                }
            }
        }
    }
}
