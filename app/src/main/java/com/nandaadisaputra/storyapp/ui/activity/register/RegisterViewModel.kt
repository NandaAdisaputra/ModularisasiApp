package com.nandaadisaputra.storyapp.ui.activity.register

import com.nandaadisaputra.storyapp.core.base.viewmodel.BaseViewModel
import com.nandaadisaputra.storyapp.core.data.remote.repository.StoryRepository
import com.nandaadisaputra.storyapp.core.data.remote.story.request.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: StoryRepository
) : BaseViewModel() {
    fun registerRequest(registerRequest: RegisterRequest) =
        repository.repositoryRegister(registerRequest)
}