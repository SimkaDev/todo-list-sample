package com.simka.todolistsample

import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.simka.todolistsample.model.Todo
import com.simka.todolistsample.repository.TodoRepository
import com.simka.todolistsample.ui.MainViewModel
import org.junit.Before

import org.junit.Rule
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var mockRepository : TodoRepository

    private val fakeTodo = Todo(1, "TITLE")

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = MainViewModel(mockRepository)
    }

    @Test
    fun getTodoReturnCorrectTodoList() {
        val liveData = MutableLiveData<List<Todo>>()
        liveData.postValue(listOf(fakeTodo))

        `when`(mockRepository.getTodoList())
            .thenReturn(liveData)

        assertEquals(viewModel.getTodoList().value, listOf(fakeTodo))
        verify(mockRepository).getTodoList();
    }
}