package br.com.loubake.androidmovies.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.loubake.androidmovies.R
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.presentation.viewmodel.MovieDetailsViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private lateinit var currentMovie: Movie
    private lateinit var movieImage: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieDescription: TextView
    private lateinit var loadingProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initViews()
        setupObservables()

        intent.extras?.getString(EXTRA_MOVIE_ID)?.let { movieId ->
            movieDetailsViewModel.getMovieDetails(movieId)
        }
    }

    private fun initViews() {
        movieImage = findViewById(R.id.movie_details_image)
        movieTitle = findViewById(R.id.movie_details_title)
        movieDescription = findViewById(R.id.movie_details_description)
        loadingProgress = findViewById(R.id.movie_details_progress)
    }

    private fun setupObservables() {
        movieDetailsViewModel.movieLiveData.observe(
            this
        ) { movieResponse ->
            currentMovie = movieResponse
            populateViews(currentMovie)
        }

        movieDetailsViewModel.notifyRequestFinishedLiveData.observe(
            this
        ) {
            loadingProgress.visibility = View.GONE
        }
    }

    private fun populateViews(movie: Movie) {
        movie.backdropUrl?.let {
            Glide.with(this)
                .load(movie.backdropUrl)
                .centerCrop()
                .into(movieImage)
        }
        movieTitle.text = movie.title
        movieDescription.text = movie.description
    }
}
