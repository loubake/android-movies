package br.com.loubake.androidmovies.presentation.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.loubake.androidmovies.R
import br.com.loubake.androidmovies.domain.Movie

class ItemMovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var itemMovieTitle: TextView

    fun bind(movie: Movie) {
        itemMovieTitle = view.findViewById(R.id.item_movie_title)
        itemMovieTitle.text = movie.title
    }
}
