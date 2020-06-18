package com.example.githubissueviewer.data

import androidx.room.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Repository::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("repo_id")
    )]
)
data class Issue(
    @PrimaryKey
    var number: Int,
    var title: String?,
    var body: String?,
    @Embedded val user: User,
    @ColumnInfo(name = "repo_id") val repoId: Int = 0
) {
}