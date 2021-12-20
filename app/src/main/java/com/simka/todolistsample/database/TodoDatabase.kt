package com.simka.todolistsample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simka.todolistsample.database.dao.TodoDao
import com.simka.todolistsample.model.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = true
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}