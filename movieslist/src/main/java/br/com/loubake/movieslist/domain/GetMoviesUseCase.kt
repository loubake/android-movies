package br.com.loubake.movieslist.domain

class GetMoviesUseCase(val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(apiKey: String) : MoviesResponse {
        return moviesRepository.getMoviesData(apiKey)
    }
}
