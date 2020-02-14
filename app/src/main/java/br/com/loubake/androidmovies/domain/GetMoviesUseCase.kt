package br.com.loubake.androidmovies.domain

class GetMoviesUseCase(val moviesRepository: MoviesRepository) {

    operator fun invoke() : MoviesResponse {
        return moviesRepository.getMoviesData()
    }
}
