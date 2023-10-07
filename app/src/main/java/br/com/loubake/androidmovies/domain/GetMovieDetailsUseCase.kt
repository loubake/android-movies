package br.com.loubake.androidmovies.domain

class GetMovieDetailsUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: String): MovieDetailsResponse {
        return moviesRepository.getMovieDetailsData(movieId = movieId)
    }
}
