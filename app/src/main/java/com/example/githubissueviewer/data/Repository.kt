package com.example.githubissueviewer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repository(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int = 0,
    var org: String?,
    var repo: String?
)