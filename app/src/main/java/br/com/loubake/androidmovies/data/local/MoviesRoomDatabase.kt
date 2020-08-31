package br.com.loubake.androidmovies.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.loubake.androidmovies.domain.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        const val MOVIE_DATABASE_NAME = "movie_database"

        @Volatile
        private var INSTANCE: MoviesRoomDatabase? = null

        fun getDatabase(context: Context): MoviesRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesRoomDatabase::class.java,
                    MOVIE_DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
