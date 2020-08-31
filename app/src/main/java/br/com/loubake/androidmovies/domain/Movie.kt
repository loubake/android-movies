package br.com.loubake.androidmovies.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.loubake.androidmovies.domain.Movie.Companion.TABLE_MOVIE

@Entity(tableName = TABLE_MOVIE)
data class Movie(
    @PrimaryKey val title: String,
    val posterUrl: String?
) {
    companion object {
        const val TABLE_MOVIE = "movie_table"
    }
}
