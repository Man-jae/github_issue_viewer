package com.example.githubissueviewer.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Issue(
    @PrimaryKey
    var number: Int,
    var title: String?,
    var body: String?,
    @Embedded val user: User,
    @ColumnInfo(name = "repo_id") val repoId: Int = 0
) {
}