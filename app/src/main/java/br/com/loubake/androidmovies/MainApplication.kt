package br.com.loubake.androidmovies

import android.app.Application
import br.com.loubake.androidmovies.di.moviesModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            this,
            listOf(
                moviesModule
            )
        )
    }
}
