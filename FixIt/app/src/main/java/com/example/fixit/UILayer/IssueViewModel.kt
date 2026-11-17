package com.example.fixit.UILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fixit.DataLayer.InMemoryIssueRepository
import com.example.fixit.DataLayer.Issue
import com.example.fixit.DataLayer.IssueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class IssueViewModel(private val repository: IssueRepository = InMemoryIssueRepository())
    : ViewModel() {

    // issues list
    // current Issue

    val issues : StateFlow<List<Issue>> = repository.getIssues() as StateFlow<List<Issue>>
    private val _currentIssueId = MutableStateFlow<String?>(null)
    val currentIssueId : StateFlow<String?> = _currentIssueId.asStateFlow()

    fun addIssue(title: String, category: String, description: String, specialistId: String? = null, status: String = "Pending") {
        viewModelScope.launch {
            val newId = UUID.randomUUID().toString()
            val issue = Issue(
                id = newId,
                title = title,
                category = category,
                description = description,
                reportedAt = System.currentTimeMillis(),
                specialistId = specialistId,
                status = status
            )
           repository.addIssue(issue)
            _currentIssueId.value = newId
        }
        // update UI state issues
        // update repository issues
    }
    fun updateIssueSpecialist(issueId: String, specialistId: String) {
        viewModelScope.launch {
            // Find current issue in issues flow or repository
            val currentList = issues.value
            val target = currentList.find { it.id == issueId } ?: repository.getIssue(issueId)
            if (target != null) {
                val updated = target.copy(specialistId = specialistId)
                repository.updateIssue(updated)
            }
        }
    }

    fun updateIssue(issue: Issue) {
        viewModelScope.launch {
            repository.updateIssue(issue)
        }
    }

    fun deleteIssue(issueId: String) {
        viewModelScope.launch {
            repository.deleteIssue(issueId)
            if (_currentIssueId.value == issueId) {
                _currentIssueId.value = null
            }
        }
    }
}