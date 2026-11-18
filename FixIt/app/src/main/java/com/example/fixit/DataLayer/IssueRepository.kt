package com.example.fixit.DataLayer

import com.example.fixit.DataLayer.RoomDB.Issue
import kotlinx.coroutines.flow.Flow

interface IssueRepository {

    fun getIssues(): Flow<List<Issue>>
    suspend fun addIssue(issue: Issue)
    suspend fun updateIssue(issue: Issue)
    suspend fun deleteIssue(issueId: String)
    suspend fun getIssue(issueId: String): Issue?

}


