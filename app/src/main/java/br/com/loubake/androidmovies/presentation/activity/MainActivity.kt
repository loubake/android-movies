package br.com.loubake.androidmovies.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.loubake.androidmovies.R
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.presentation.adapter.MoviesAdapter
import br.com.loubake.androidmovies.presentation.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val GRID_COLUMS = 2
    }

    private val listMovies = mutableListOf<Movie>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        initRecyclerView()
    }

    private fun initViews() {
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        moviesRecyclerView = findViewById(R.id.main_recycler_movies)
    }

    private fun initRecyclerView() {
        listMovies.add(Movie("title1", null))
        listMovies.add(Movie("title2", null))
        listMovies.add(Movie("title3", null))

        moviesRecyclerView.layoutManager = GridLayoutManager(this,
            GRID_COLUMS
        )
        moviesRecyclerView.adapter =
            MoviesAdapter(this, listMovies)
    }
}