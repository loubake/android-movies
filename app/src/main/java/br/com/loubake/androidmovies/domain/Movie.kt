package br.com.loubake.androidmovies.domain

data class Movie(
    val id: String,
    val title: String?,
    val description: String?,
    val releaseDate: String?,
    val genres: List<String>?,
    val tagline: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val backdropImages: List<String>? = null,
    val posterImages: List<String>? = null,
)
