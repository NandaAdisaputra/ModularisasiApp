package com.nandaadisaputra.storyapp.core.ui.activity.map

import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.remote.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : BaseViewModel() {
    fun getStoryLocation(token: String) = storyRepository.getLocation(token)
}