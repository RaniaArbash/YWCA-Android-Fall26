package com.example.fixit.DataLayer.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Issue::class], version = 1)
abstract class IssueDatabase : RoomDatabase() {
    abstract fun IssueDao(): IssueDAO

    companion object {
        private var INSTANCE : IssueDatabase? = null

        // keep only one instance of this db
        fun getDatabase(context: Context) : IssueDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    IssueDatabase::class.java,
                    "issuedb").build()
                INSTANCE = instance
                instance
            }

        }
    }


}



