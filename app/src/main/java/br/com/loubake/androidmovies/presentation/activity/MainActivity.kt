package br.com.loubake.androidmovies.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.loubake.androidmovies.R
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.presentation.adapter.MoviesAdapter
import br.com.loubake.androidmovies.presentation.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val GRID_COLUMS = 2
    }

    private val listMovies = mutableListOf<Movie>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesProgress: ProgressBar
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        initRecyclerView()

        setupObservables()

        moviesViewModel.getMovies()
    }

    private fun initViews() {
        moviesRecyclerView = findViewById(R.id.main_recycler_movies)
        moviesProgress = findViewById(R.id.main_progress)
    }

    private fun initRecyclerView() {
        moviesRecyclerView.layoutManager = GridLayoutManager(this, GRID_COLUMS)
        moviesRecyclerView.adapter = MoviesAdapter(this, listMovies).apply {
            this.movieClickListener = onMovieClick()
        }
    }

    private fun setupObservables() {
        moviesViewModel.moviesListLiveData.observe(
            this,
            Observer { moviesResponse ->
                moviesProgress.visibility = View.GONE
                moviesResponse.map { movie -> listMovies.add(movie) }
                moviesRecyclerView.adapter?.notifyDataSetChanged()
            }
        )

        moviesViewModel.notifyRequestFinishedLiveData.observe(
            this,
            Observer {
                moviesProgress.visibility = View.GONE
            }
        )
    }

    private fun onMovieClick() = { movieId: String ->
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            this.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId)
        }
        startActivity(intent)
    }
}
