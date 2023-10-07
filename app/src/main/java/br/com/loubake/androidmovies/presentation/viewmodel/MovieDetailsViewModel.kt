package br.com.loubake.androidmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.loubake.androidmovies.domain.GetMovieDetailsUseCase
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.domain.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val useCaseGetMovieDetails: GetMovieDetailsUseCase
) :ViewModel() {
    val movieLiveData = MutableLiveData<Movie>()
    val notifyRequestFinishedLiveData = MutableLiveData<Unit>()

    fun getMovieDetails(movieId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = useCaseGetMovieDetails(movieId = movieId)

            when (result.status) {
                ResponseStatus.SUCCESS -> {
                    notifyRequestFinishedLiveData.postValue(Unit)
                    movieLiveData.postValue(result.movie)
                }
                else -> {
                    notifyRequestFinishedLiveData.postValue(Unit)
                }
            }
        }
    }
}
