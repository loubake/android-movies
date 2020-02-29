package br.com.loubake.movieslist.di

import br.com.loubake.movieslist.data.MoviesRepositoryImpl
import br.com.loubake.movieslist.data.MoviesService
import br.com.loubake.movieslist.domain.GetMoviesUseCase
import br.com.loubake.movieslist.domain.MoviesRepository
import br.com.loubake.movieslist.presentation.viewmodel.MoviesViewModel
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

    factory {
        GetMoviesUseCase(
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
