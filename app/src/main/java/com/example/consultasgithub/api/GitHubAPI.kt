package com.example.consultasgithub.api


import com.example.consultasgithub.models.GitRepositoriesResponse
import com.example.consultasgithub.models.PullResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubAPI {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getRepositories(
        @Query("page") page:Int = 1
    ): Response<GitRepositoriesResponse>

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun searchForRepositories(
        @Query("q")
        searchQuery: String?,
        @Query("page")
        pageNumber: Int = 1,
    ): Response<GitRepositoriesResponse>

    @GET("/repos/{owner}/{repo}/pulls")
    suspend fun getPulls(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page")
        pageNumber: Int = 1,
    ): Response<PullResponse>
}