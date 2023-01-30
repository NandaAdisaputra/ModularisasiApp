package com.nandaadisaputra.storyapp.ui.activity.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.local.datastore.DataStorePreference
import com.nandaadisaputra.storyapp.core.data.remote.repository.FavoriteRepository
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(application: Application, private val dataStorePreference: DataStorePreference) : BaseViewModel() {
    private val mFavoriteRepository: FavoriteRepository?
    init {
        mFavoriteRepository = FavoriteRepository(application)
    }

    fun getAllFavorites(): LiveData<List<FavoriteEntity>>? = mFavoriteRepository?.getAllFavorites()
    val getTheme = dataStorePreference.getTheme().asLiveData(Dispatchers.IO)


    fun setTheme(isDarkMode : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePreference.setTheme(isDarkMode)
        }
    }

}