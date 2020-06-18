package com.example.githubissueviewer.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userImage: String
) : Parcelable