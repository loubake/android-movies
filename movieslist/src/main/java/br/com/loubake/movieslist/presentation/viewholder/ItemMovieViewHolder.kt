package br.com.loubake.movieslist.presentation.viewholder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.loubake.movieslist.R
import br.com.loubake.movieslist.domain.Movie
import com.bumptech.glide.Glide

class ItemMovieViewHolder(val context: Context, val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var itemMovieTitle: TextView
    private lateinit var itemMovieImage: ImageView

    fun bind(movie: Movie) {
        itemMovieTitle = view.findViewById(R.id.item_movie_title)
        itemMovieImage = view.findViewById(R.id.item_movie_image)

        itemMovieTitle.text = movie.title

        Glide.with(context)
            .load(movie.posterUrl)
            .centerCrop()
            .into(itemMovieImage)
    }
}
