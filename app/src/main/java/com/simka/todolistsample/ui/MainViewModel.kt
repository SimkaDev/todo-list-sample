package com.simka.todolistsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.simka.todolistsample.model.Todo
import com.simka.todolistsample.repository.TodoRepository

class MainViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    fun getTodoList(): LiveData<List<Todo>> {
        return todoRepository.getTodoList()
    }

}