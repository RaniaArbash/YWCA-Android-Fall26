package com.example.fixit.DataLayer.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="issues")
data class Issue(
    @PrimaryKey
    val id: String,
    val title: String,
    val category: String,
    val description: String,
    val reportedAt: Long,
    val specialistId: String? = null, // very important

    val status: String = "Pending"
)

data class Specialist(
    val id: String,
    val name: String,
    val expertise: String,
    val description: String,
    val phone: String,
    val email: String,
    val officeAddress: String,
    val profileUrl: String
)