package com.aslan.okko.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aslan.okko.model.fav.FavList
import com.aslan.okko.servis.FilmDAO

@Database(entities = [FavList::class], version = 1)
abstract class FilmFavDataBase : RoomDatabase() {

    abstract fun favFilmDao(): FilmDAO

    companion object {
        @Volatile
        private var instance: FilmFavDataBase? = null
        private val lock = Any()

        fun getInstance(context: Context): FilmFavDataBase {
            return instance ?: synchronized(lock) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): FilmFavDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                FilmFavDataBase::class.java,
                "filmFavDatabase"
            ).build()
        }
    }
}
