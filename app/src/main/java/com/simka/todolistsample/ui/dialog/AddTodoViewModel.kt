package com.simka.todolistsample.ui.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simka.todolistsample.model.Todo
import com.simka.todolistsample.repository.TodoRepository
import kotlinx.coroutines.launch

class AddTodoViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    private val _todoSaved = MutableLiveData<Boolean>()
    val todoSaved: LiveData<Boolean> = _todoSaved

    private val _savedError = MutableLiveData<Boolean>()
    val savedError: LiveData<Boolean> = _savedError

    var titleTodo: String? = null
    var descriptionTodo: String? = null

    fun canBeSaved(): Boolean {
        return !titleTodo.isNullOrBlank()
    }

    fun saveTodo() {
        if (canBeSaved()) {
            val todo = Todo(title = titleTodo!!, description = descriptionTodo ?: "")
            viewModelScope.launch {
                todoRepository.saveTodo(todo)
                _todoSaved.postValue(true)
            }
        } else {
            _savedError.postValue(true)
        }
    }
}