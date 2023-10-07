package br.com.loubake.androidmovies.domain

data class Movie(
    val id: String,
    val title: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val description: String?
)
