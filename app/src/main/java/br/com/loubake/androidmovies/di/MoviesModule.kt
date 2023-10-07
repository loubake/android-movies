package br.com.loubake.androidmovies.di

import br.com.loubake.androidmovies.data.MoviesRepositoryImpl
import br.com.loubake.androidmovies.data.MoviesService
import br.com.loubake.androidmovies.domain.GetMovieDetailsUseCase
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.MoviesRepository
import br.com.loubake.androidmovies.presentation.viewmodel.MovieDetailsViewModel
import br.com.loubake.androidmovies.presentation.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moviesModule = module {

    viewModel {
        MoviesViewModel(
            get<GetMoviesUseCase>()
        )
    }

    viewModel {
        MovieDetailsViewModel(
            get<GetMovieDetailsUseCase>()
        )
    }

    factory {
        GetMoviesUseCase(
            get<MoviesRepository>()
        )
    }

    factory {
        GetMovieDetailsUseCase(
            get<MoviesRepository>()
        )
    }

    single {
        MoviesRepositoryImpl(
            get<MoviesService>()
        ) as MoviesRepository
    }

    single {
        createMoviesService(
            get<Retrofit>()
        )
    }

    single {
        createRetrofit()
    }
}

fun createRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(MoviesService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createMoviesService(retrofit: Retrofit): MoviesService {
    return retrofit.create(MoviesService::class.java)
}
