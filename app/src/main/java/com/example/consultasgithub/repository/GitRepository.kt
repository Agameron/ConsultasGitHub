package com.example.consultasgithub.repository


import com.example.consultasgithub.api.RetrofitInstance

//import com.example.consultasgithub.db.RepoDatabase


class GitRepository(
//    val db: RepoDatabase.Companion
) {
    suspend fun getRepo() =
        RetrofitInstance.api.getRepositories()

    suspend fun getPulls(
        pageNumber: Int,
        owner: String,
        repo: String,
    ) =
        RetrofitInstance.api.getPulls(
            owner = owner,
            repo = repo,
            pageNumber = pageNumber,
        )
}