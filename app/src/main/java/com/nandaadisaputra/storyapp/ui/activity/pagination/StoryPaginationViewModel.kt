package com.nandaadisaputra.storyapp.ui.activity.pagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.remote.StoryRemoteRepository
import com.nandaadisaputra.storyapp.core.data.remote.story.StoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryPaginationViewModel @Inject constructor(
    private val remoteStoryRepository: StoryRemoteRepository

) : BaseViewModel() {
    fun getStory(token: String) : LiveData<PagingData<StoryEntity>> = remoteStoryRepository.repositoryListStory(token).cachedIn(viewModelScope)
}