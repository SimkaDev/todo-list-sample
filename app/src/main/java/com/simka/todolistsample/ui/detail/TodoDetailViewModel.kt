package com.simka.todolistsample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simka.todolistsample.model.Todo

class TodoDetailViewModel() : ViewModel() {

    private val _todoDetails = MutableLiveData<Todo>()
    val todoDetailsLiveData: LiveData<Todo> = _todoDetails

    fun setTodo(photo: Todo) {
        _todoDetails.postValue(photo)
    }

}