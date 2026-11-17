package com.example.fixit.DataLayer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryIssueRepository : IssueRepository {

    private val issuesFlow = MutableStateFlow<List<Issue>>(emptyList())

    override fun getIssues(): Flow<List<Issue>> {
        return issuesFlow.asStateFlow()
    }

    override suspend fun addIssue(issue: Issue) {
        val currentList = issuesFlow.value
        issuesFlow.value =  currentList + issue
    }

    override suspend fun updateIssue(issue: Issue) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteIssue(issueId: String) {
        val currentList = issuesFlow.value
        issuesFlow.value =  currentList.filter { it.id != issueId }
    }

    override suspend fun getIssue(issueId: String): Issue? {
        return issuesFlow.value.find { it.id == issueId }

    }


}