package com.example.githubissueviewer.model

import com.google.gson.annotations.SerializedName

data class IssueModel(
    @SerializedName("number")
    val number: Int,
    @SerializedName("title")
    val title: String
)