package br.com.loubake.androidmovies.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.loubake.androidmovies.R
import br.com.loubake.androidmovies.domain.Movie
import br.com.loubake.androidmovies.presentation.viewholder.ItemMovieViewHolder

class MoviesAdapter(val context: Context, val moviesList: List<Movie>): RecyclerView.Adapter<ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, null, false)
        return ItemMovieViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}
