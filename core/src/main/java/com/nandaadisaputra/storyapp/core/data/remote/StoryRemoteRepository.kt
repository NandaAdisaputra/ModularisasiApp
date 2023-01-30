package com.nandaadisaputra.storyapp.core.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.nandaadisaputra.storyapp.core.api.ApiService
import com.nandaadisaputra.storyapp.core.data.room.database.StoryDatabase
import com.nandaadisaputra.storyapp.core.data.remote.story.StoryEntity

class StoryRemoteRepository(
    private val storyDatabase: StoryDatabase,
    private val apiService: ApiService
) {
    fun repositoryListStory(token: String) : LiveData<PagingData<StoryEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager (
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(token, storyDatabase, apiService),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStoryPagingSource()
            }
        ).liveData
    }
}