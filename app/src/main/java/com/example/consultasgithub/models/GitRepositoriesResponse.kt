package com.example.consultasgithub.models


import com.google.gson.annotations.SerializedName

data class GitRepositoriesResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<RepositoryItem>?,
    @SerializedName("total_count")
    val totalCount: Int?
)