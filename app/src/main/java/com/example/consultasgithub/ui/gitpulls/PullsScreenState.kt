package com.example.consultasgithub.ui.gitpulls

import com.example.consultasgithub.models.PullResponseItem

sealed class PullsScreenState {
    object Loading: PullsScreenState()
    data class Error(val errorMessage: String): PullsScreenState()
    data class Success(val pulls:ArrayList<PullResponseItem>): PullsScreenState()
}