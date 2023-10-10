package br.com.loubake.androidmovies.data

import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("results")
    val results: List<MovieDTO>
)

data class MovieDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("genres")
    val genres: List<GenreDTO>?,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("images")
    val images: MovieImagesListDTO,
) {
    companion object {
        const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p"
        const val POSTER_SIZE = "/w780"
        const val BACKDROP_SIZE = "/w1280"
    }

    fun getPosterUrl(): String {
        return IMAGES_BASE_URL + POSTER_SIZE + posterPath
    }

    fun getBackdropUrl(): String {
        return IMAGES_BASE_URL + BACKDROP_SIZE + backdropPath
    }

    fun getGenresNames(): List<String>? {
        return genres?.map { it.name }
    }

    fun getBackdropUrlsList(): List<String> {
        return images.backdrops.mapNotNull {
            IMAGES_BASE_URL + BACKDROP_SIZE + it.filePath
        }
    }

    fun getPosterUrlsList(): List<String> {
        return images.posters.mapNotNull {
            it.filePath
        }
    }
}

data class GenreDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
)

data class MovieImagesListDTO(
    @SerializedName("backdrops")
    val backdrops: List<MovieImageDTO>,
    @SerializedName("logos")
    val logos: List<MovieImageDTO>,
    @SerializedName("posters")
    val posters: List<MovieImageDTO>,
)

data class MovieImageDTO(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,
    @SerializedName("height")
    val height: Double?,
    @SerializedName("iso_639_1")
    val iso639: String?,
    @SerializedName("file_path")
    val filePath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("width")
    val width: Double?,
)
