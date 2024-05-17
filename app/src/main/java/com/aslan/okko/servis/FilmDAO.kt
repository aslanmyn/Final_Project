package com.aslan.okko.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aslan.okko.model.fav.FavList
@Dao
interface FilmDAO {

    //data access
    @Insert
     fun insertAll(favList: FavList)


    @Query("SELECT*FROM favList")
     fun getAllFav():List<FavList>

    @Query("DELETE FROM favList")
     fun deleteAllFav()

    @Query("DELETE FROM FavList WHERE uid = :filmId")
    fun deleteFav(filmId: Int)

    @Query("SELECT * FROM FavList WHERE id = :filmId")
    fun getFilmByName(filmId: String): FavList?



}