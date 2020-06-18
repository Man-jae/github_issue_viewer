package com.example.githubissueviewer.data

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    foreignKeys = [ForeignKey(
        entity = Repository::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("repo_id"),
        onDelete = CASCADE
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
    companion object {
        fun convert(issue: IssueResponse): Issue {
            return Issue(
                number = issue.number,
                title = issue.title,
                body = issue.body,
                user = User.convert(issue.user)
            )
        }
    }
}