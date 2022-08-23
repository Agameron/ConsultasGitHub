package com.example.consultasgithub.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullResponseItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("html_url")
    val htmlUrl: String?,
    @SerializedName("user")
    val user: UserXXX?
):Parcelable