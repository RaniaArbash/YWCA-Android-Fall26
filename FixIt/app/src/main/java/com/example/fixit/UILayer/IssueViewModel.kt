package com.example.fixit.UILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fixit.DataLayer.RoomDB.Issue
import com.example.fixit.DataLayer.IssueRepository
import com.example.fixit.DataLayer.RoomDBIssueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class IssueViewModel(
    private val repository: IssueRepository) : ViewModel() {

    val issues : StateFlow<List<Issue>> = repository.getIssues().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    private val _currentIssueId = MutableStateFlow<String?>(null)
    val currentIssueId : StateFlow<String?> = _currentIssueId.asStateFlow()

    fun setCurrentIssueId(issueId: String?) {
        _currentIssueId.value = issueId
    }

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

    companion object {
        fun provideFactory(repository: IssueRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(IssueViewModel::class.java)) {
                        return IssueViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }



}