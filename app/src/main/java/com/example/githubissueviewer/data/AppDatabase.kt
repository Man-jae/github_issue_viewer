package com.example.githubissueviewer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubissueviewer.data.AppDatabase.Companion.DB_VERSION

@Database(entities = [Repository::class, Issue::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepositoryDao
    abstract fun issueDao(): IssueDao

    companion object {
        private const val DB_NAME = "issue"
        const val DB_VERSION = 1

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also {
                    INSTANCE = it
                }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
    }
}