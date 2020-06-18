package com.example.githubissueviewer.data

import androidx.room.*

@Dao
interface RepositoryDao {
    @Query("SELECT * FROM Repository WHERE id = 0")
    fun read(): Repository?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(repo: Repository)

    @Query("DELETE FROM Repository")
    fun delete()
}