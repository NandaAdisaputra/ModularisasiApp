package com.nandaadisaputra.storyapp.ui.activity.detail

import android.app.Application
import android.view.View
import androidx.lifecycle.asLiveData
import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.local.datastore.DataStorePreference
import com.nandaadisaputra.storyapp.core.data.remote.repository.FavoriteRepository
import com.nandaadisaputra.storyapp.core.data.room.database.StoryDatabase
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteDao
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val dataStorePreference: DataStorePreference
) : BaseViewModel() {
    private var favoriteDAO: FavoriteDao?
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)
    private var favoriteStoryDatabase: StoryDatabase? =
        StoryDatabase.getDatabase(application)

    init {
        favoriteDAO = favoriteStoryDatabase?.favoriteDao()
    }
    fun checkUser(id: String) = mFavoriteRepository.check(id)

    fun addToFavorite(
        id: String,
        name: String?,
        photoUrl: String,
        createdAt: String?,
        description: String?,
        lat: Double?,
        lon: Double?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val favorite = FavoriteEntity(
                id,
                name,
                photoUrl,
                createdAt,
                description,
                lat,
                lon
            )
            mFavoriteRepository.insert(favorite)
        }
    }

    fun removeFromFavorite(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            mFavoriteRepository.delete(id)
        }
    }

    val getTheme = dataStorePreference.getTheme().asLiveData(Dispatchers.IO)
}