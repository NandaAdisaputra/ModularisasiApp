package com.nandaadisaputra.storyapp.core.data.room.favorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteEntity)

    @Query("SELECT count(*) FROM favorite WHERE favorite.id = :id")
    fun check(id: String): Int

    @Query("DELETE FROM favorite WHERE favorite.id = :id")
    fun delete(id: String): Int

    @Query("SELECT * from favorite")
    fun getAllFavorites(): LiveData<List<FavoriteEntity>>
}