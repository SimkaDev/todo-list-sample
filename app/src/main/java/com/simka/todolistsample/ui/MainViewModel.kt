package com.simka.todolistsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simka.todolistsample.model.Todo
import com.simka.todolistsample.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    fun getTodoList(): LiveData<List<Todo>> {
        return todoRepository.getTodoList()
    }

    fun updateTodoStatus(isChecked: Boolean, todo: Todo) {
        todo.isDone = isChecked

        viewModelScope.launch {
            todoRepository.updateTodo(todo)
        }
    }

}