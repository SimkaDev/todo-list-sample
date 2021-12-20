package com.simka.todolistsample.repository

import androidx.lifecycle.LiveData
import com.simka.todolistsample.database.TodoDatabase
import com.simka.todolistsample.model.Todo

class TodoRepositoryImpl(private val database: TodoDatabase): TodoRepository {

    override fun getTodoList() : LiveData<List<Todo>> =
        database.todoDao().getTodoList()

    override suspend fun updateTodo(todo: Todo) {
        database.todoDao().updateTodo(todo)
    }
}