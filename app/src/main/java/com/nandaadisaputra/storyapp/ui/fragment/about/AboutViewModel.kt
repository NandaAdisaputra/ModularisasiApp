package com.nandaadisaputra.storyapp.ui.fragment.about

import androidx.lifecycle.asLiveData
import com.nandaadisaputra.storyapp.core.api.ApiService
import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.local.datastore.DataStorePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val apiService: ApiService,
    private val dataStorePreference: DataStorePreference
) : BaseViewModel() {
    val getTheme = dataStorePreference.getTheme().asLiveData(Dispatchers.IO)
}