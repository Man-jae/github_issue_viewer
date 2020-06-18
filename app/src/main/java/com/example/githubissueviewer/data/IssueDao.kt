package com.example.githubissueviewer.data

import androidx.room.*


@Dao
interface IssueDao {
    @Query("SELECT * FROM Issue")
    fun readAll(): List<Issue>?

    @Query("SELECT * FROM Issue WHERE number = :number")
    fun read(number: Int): Issue

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(issues: List<Issue>)
}