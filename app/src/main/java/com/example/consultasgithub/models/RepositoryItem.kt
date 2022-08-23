package com.example.consultasgithub.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class RepositoryItem(
    @SerializedName("description")
    val description: String?,
    @SerializedName("forks_count")
    val forksCount: Int?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("owner")
    val owner: OwnerX?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int?,
):Parcelable