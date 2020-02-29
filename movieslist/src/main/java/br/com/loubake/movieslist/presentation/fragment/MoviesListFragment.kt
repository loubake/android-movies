package br.com.loubake.movieslist.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.loubake.movieslist.R
import br.com.loubake.movieslist.domain.Movie
import br.com.loubake.movieslist.presentation.adapter.MoviesAdapter
import br.com.loubake.movieslist.presentation.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment() {

    companion object {
        const val GRID_COLUMS = 2

        fun newInstance(
            apiKey: String,
            finishedLoadingSuccess: () -> Unit,
            finishedLoadingError: () -> Unit
        ): MoviesListFragment {
            val fragment = MoviesListFragment()
            fragment.apiKey = apiKey
            fragment.finishedLoadingSuccess = finishedLoadingSuccess
            fragment.finishedLoadingError = finishedLoadingError
            return fragment
        }
    }

    private val moviesViewModel: MoviesViewModel by viewModel()
    private val listMovies = mutableListOf<Movie>()
    private lateinit var apiKey: String
    private lateinit var finishedLoadingSuccess: () -> Unit
    private lateinit var finishedLoadingError: () -> Unit
    private lateinit var moviesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel.getMovies(apiKey)

        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initRecyclerView()
        setupObservables()
    }

    private fun initViews(view: View) {
        moviesRecyclerView = view.findViewById(R.id.main_recycler_movies)
    }

    private fun initRecyclerView() {
        context.let {
            moviesRecyclerView.layoutManager = GridLayoutManager(it, GRID_COLUMS)
            moviesRecyclerView.adapter = MoviesAdapter(it!!, listMovies)
        }
    }

    private fun setupObservables() {
        activity?.let {
            moviesViewModel.moviesListLiveData.observe(
                it,
                Observer { moviesResponse ->
                    moviesResponse.map { movie -> listMovies.add(movie) }
                    moviesRecyclerView.adapter?.notifyDataSetChanged()
                }
            )

            moviesViewModel.notifyRequestFinishedSucessLiveData.observe(
                it,
                Observer {
                    finishedLoadingSuccess()
                }
            )

            moviesViewModel.notifyRequestFinishedErrorLiveData.observe(
                it,
                Observer {
                    finishedLoadingError()
                }
            )
        }
    }
}
