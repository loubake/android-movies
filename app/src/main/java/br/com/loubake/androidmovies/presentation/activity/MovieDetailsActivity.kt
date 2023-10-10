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
    private lateinit var movieBackdropImage: ImageView
    private lateinit var moviePosterImage: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieTagline: TextView
    private lateinit var movieGenres: TextView
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
        movieBackdropImage = findViewById(R.id.movie_details_backdrop_image)
        moviePosterImage = findViewById(R.id.movie_details_poster_image)
        movieTitle = findViewById(R.id.movie_details_title)
        movieReleaseDate = findViewById(R.id.movie_details_release_date)
        movieTagline = findViewById(R.id.movie_details_tagline)
        movieGenres = findViewById(R.id.movie_details_genres)
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
        movie.backdropUrl?.let { backdropUrl ->
            loadBackdropImage(backdropUrl)
        }
        movieTitle.text = movie.title
        movieTagline.text = movie.tagline
        movieReleaseDate.text = movie.releaseDate
        movieGenres.text = movie.genres.toString()
        movie.posterUrl?.let { posterUrl ->
            loadPosterImage(posterUrl)
        }
        movieDescription.text = movie.description
    }

    private fun loadBackdropImage(backdropImageUrl: String) {
        Glide.with(this)
            .load(backdropImageUrl)
            .centerCrop()
            .into(movieBackdropImage)
    }

    private fun loadPosterImage(posterImageUrl: String) {
        Glide.with(this)
            .load(posterImageUrl)
            .centerCrop()
            .into(moviePosterImage)
    }
}
