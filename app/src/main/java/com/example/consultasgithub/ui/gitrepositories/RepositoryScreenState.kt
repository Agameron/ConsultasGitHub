package com.example.consultasgithub.ui.gitrepositories

import com.example.consultasgithub.models.RepositoryItem

sealed class RepositoryScreenState{
    object Loading: RepositoryScreenState()
    data class Error(val errorMessage: String): RepositoryScreenState()
    data class Success(val repositories:MutableList<RepositoryItem>): RepositoryScreenState()
    data class PullsSuccess(val repo:RepositoryItem): RepositoryScreenState()
}

