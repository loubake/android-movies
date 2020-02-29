package br.com.loubake.movieslist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.loubake.movieslist.domain.GetMoviesUseCase
import br.com.loubake.movieslist.domain.Movie
import br.com.loubake.movieslist.domain.MoviesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    val useCaseGetMovies: GetMoviesUseCase
) : ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Movie>>()
    val notifyRequestFinishedSucessLiveData = MutableLiveData<Unit>()
    val notifyRequestFinishedErrorLiveData = MutableLiveData<Unit>()

    fun getMovies(apiKey: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = useCaseGetMovies(apiKey)

            when (result.status) {
                MoviesResponse.Status.SUCCESS -> {
                    notifyRequestFinishedSucessLiveData.postValue(Unit)
                    moviesListLiveData.postValue(result.listMovies)
                }
                MoviesResponse.Status.ERROR_API -> {
                    notifyRequestFinishedErrorLiveData.postValue(Unit)
                }
            }
        }
    }
}
