package com.example.fixit.DataLayer

import com.example.fixit.DataLayer.RoomDB.Issue
import com.example.fixit.DataLayer.RoomDB.IssueDAO
import kotlinx.coroutines.flow.Flow

class RoomDBIssueRepository(private val issueDao: IssueDAO) :
    IssueRepository {

    override fun getIssues(): Flow<List<Issue>> {
        return issueDao.getAll()
    }

    override suspend fun addIssue(issue: Issue) {
       issueDao.insertIssue(issue)
    }

    override suspend fun updateIssue(issue: Issue) {
       issueDao.updateIssue(issue)
    }

    override suspend fun deleteIssue(issueId: String) {
      // issueDao.deleteIssue(issueId)
    }

    override suspend fun getIssue(issueId: String): Issue? {
        return issueDao.getIssueByIdy(issueId)
    }

}