package com.example.githubissueviewer.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userImage: String
) : Parcelable