package com.example.consultasgithub.ui.gitrepositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consultasgithub.models.*
//import com.example.consultasgithub.db.RepoDatabase
import com.example.consultasgithub.repository.GitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RepoViewModel : ViewModel() {

    private val gitRepository = GitRepository()

    val screenState: MutableLiveData<RepositoryScreenState> = MutableLiveData()



    fun getRepo() = viewModelScope.launch {
        screenState.postValue(RepositoryScreenState.Loading)
        val response = gitRepository.getRepo()

        screenState.postValue(handleRepositoriesResponse(response))
    }

    fun getPulls(repositoryItem: RepositoryItem) {

        screenState.postValue(RepositoryScreenState.PullsSuccess(repositoryItem))
    }

    private fun handleRepositoriesResponse(response: Response<GitRepositoriesResponse>): RepositoryScreenState {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val list = mutableListOf<RepositoryItem>()
                list.addAll(resultResponse.items.orEmpty())
                return RepositoryScreenState.Success(list)
            }
        }
        return RepositoryScreenState.Error(response.message())
    }
}
