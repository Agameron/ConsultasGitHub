package com.example.consultasgithub.ui.gitpulls

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consultasgithub.models.PullResponse
import com.example.consultasgithub.models.RepositoryItem
import com.example.consultasgithub.repository.GitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PullRequestViewModel:ViewModel() {
    private val gitRepository = GitRepository()

    val screenState: MutableLiveData<PullsScreenState> = MutableLiveData()

    fun getPulls(pullsPage: Int = 1,repositoryItem: RepositoryItem) = viewModelScope.launch {
        screenState.postValue(PullsScreenState.Loading)
        val response = gitRepository.getPulls(
            pageNumber = pullsPage,
            owner = repositoryItem.owner?.login.orEmpty(),
            repo = repositoryItem.name.orEmpty(),
        )
        screenState.postValue(handlePullsResponse(response))
    }
    }
    private fun handlePullsResponse(response: Response<PullResponse>): PullsScreenState {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return PullsScreenState.Success(resultResponse)
            }
        }
        return PullsScreenState.Error(response.message())
    }
