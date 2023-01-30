package com.nandaadisaputra.storyapp.core.data.remote.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.nandaadisaputra.storyapp.core.data.room.database.StoryDatabase
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteDao
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteEntity

class FavoriteRepository (application: Application) {

    private val mFavoriteDao: FavoriteDao

    init {
        val db = StoryDatabase.getDatabase(application)
        mFavoriteDao = db.storyDao()
    }

    fun getAllFavorites(): LiveData<List<FavoriteEntity>> = mFavoriteDao.getAllFavorites()

    fun check(id: String) = mFavoriteDao.check(id)

    fun insert(favorite: FavoriteEntity) = mFavoriteDao.insert(favorite)

    fun delete(id: String) = mFavoriteDao.delete(id)
}
