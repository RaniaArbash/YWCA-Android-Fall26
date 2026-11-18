package com.example.fixit.DataLayer.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface IssueDAO {

    // get all issues
    @Query("SELECT * FROM issues ORDER BY reportedAt DESC")
    fun getAll(): Flow<List<Issue>>

    @Query("select * from issues where category = (:cat)")
    suspend fun getAllIssueWithCategory(cat: String):List<Issue>

    // insert one issue to db
    @Insert
    suspend fun insertIssue(newIssue: Issue)

    // update one issue
    @Update
    suspend fun updateIssue(updateIssue: Issue)

    // delete one issue
    @Delete
    suspend fun deleteOneIssue(todelete: Issue)

// get one issue by id
@Query("select * from issues where id = (:id)")
suspend fun getIssueByIdy(id: String): Issue




}