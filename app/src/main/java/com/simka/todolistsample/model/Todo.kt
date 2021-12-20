package com.simka.todolistsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String,
    var description: String = "",
    var isDone: Boolean = false
    )

