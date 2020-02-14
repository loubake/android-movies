package br.com.loubake.androidmovies.domain

class GetMoviesUseCase(val moviesRepository: MoviesRepository) {

    operator fun invoke() : List<Movie> {
        return moviesRepository.getMoviesData()
    }
}
