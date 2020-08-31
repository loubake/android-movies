package br.com.loubake.androidmovies.di

import android.content.Context
import br.com.loubake.androidmovies.data.local.MoviesDao
import br.com.loubake.androidmovies.data.MoviesRepositoryImpl
import br.com.loubake.androidmovies.data.local.MoviesRoomDatabase
import br.com.loubake.androidmovies.data.remote.MoviesService
import br.com.loubake.androidmovies.domain.GetMoviesUseCase
import br.com.loubake.androidmovies.domain.MoviesRepository
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

    factory {
        GetMoviesUseCase(
            get<MoviesRepository>()
        )
    }

    single {
        MoviesRepositoryImpl(
            get<MoviesService>(),
            get<MoviesDao>()
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

    single {
        createMoviesDao(context = get())
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

fun createMoviesDao(context: Context): MoviesDao {
    return MoviesRoomDatabase.getDatabase(context).moviesDao()
}
