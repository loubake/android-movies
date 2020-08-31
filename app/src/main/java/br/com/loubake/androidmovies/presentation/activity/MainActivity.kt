package br.com.loubake.androidmovies.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
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
    private lateinit var errorLayout: LinearLayout
    private lateinit var errorTextView: TextView
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesProgress: ProgressBar
    val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        initRecyclerView()

        setupObservables()

        moviesViewModel.getMovies()
    }

    private fun initViews() {
        errorLayout = findViewById(R.id.error_layout)
        moviesRecyclerView = findViewById(R.id.main_recycler_movies)
        moviesProgress = findViewById(R.id.main_progress)
    }

    private fun initRecyclerView() {
        moviesRecyclerView.layoutManager = GridLayoutManager(this, GRID_COLUMS)
        moviesRecyclerView.adapter = MoviesAdapter(this, listMovies)
    }

    private fun setupObservables() {
        moviesViewModel.moviesListLiveData.observe(
            this,
            Observer { moviesResponse ->
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

        moviesViewModel.errorLiveData.observe(
            this,
            Observer {
                errorLayout.visibility = View.VISIBLE
                errorTextView.text = getString(R.string.error_unexpected)
            }
        )
    }
}
