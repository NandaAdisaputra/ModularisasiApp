package com.nandaadisaputra.storyapp.ui.activity.add

import androidx.lifecycle.LiveData
import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.remote.Result
import com.nandaadisaputra.storyapp.core.data.remote.repository.StoryRepository
import com.nandaadisaputra.storyapp.core.data.remote.story.ListStoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
    ) : BaseViewModel() {
    fun addStory(
        token: String?,
        description: RequestBody,
        file: MultipartBody.Part
    ): LiveData<Result<ListStoryResponse>> {
        return storyRepository.addStory(token, description, file)
    }
}