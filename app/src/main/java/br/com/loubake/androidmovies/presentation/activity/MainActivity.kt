package br.com.loubake.androidmovies.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import br.com.loubake.androidmovies.BuildConfig
import br.com.loubake.androidmovies.R
import br.com.loubake.movieslist.presentation.fragment.MoviesListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainFrameLayout: FrameLayout
    private lateinit var moviesProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        addMoviesListFeature()
    }

    private fun initViews() {
        mainFrameLayout = findViewById(R.id.main_frame_layout)
        moviesProgress = findViewById(R.id.main_progress)
    }

    private fun addMoviesListFeature() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_frame_layout,
                MoviesListFragment.newInstance(
                    BuildConfig.THE_MOVIE_DB_API_KEY,
                    { finishedLoadingSuccess() },
                    { finishedLoadingError() }
                ))
            .commit()
    }

    private fun finishedLoadingSuccess() {
        moviesProgress.visibility = View.GONE
    }

    private fun finishedLoadingError() {
        moviesProgress.visibility = View.GONE
    }
}
