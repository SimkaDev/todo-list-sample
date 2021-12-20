package com.simka.todolistsample.repository

import androidx.lifecycle.LiveData
import com.simka.todolistsample.model.Todo

interface TodoRepository {
    fun getTodoList(): LiveData<List<Todo>>
    suspend fun updateTodo(todo: Todo)
    suspend fun saveTodo(todo: Todo)
}