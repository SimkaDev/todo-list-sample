package com.simka.todolistsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simka.todolistsample.database.TodoDatabase
import com.simka.todolistsample.di.roomTestModule
import com.simka.todolistsample.model.Todo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class TodoDaoTest : KoinTest {

    private val database: TodoDatabase  by inject()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Override default Koin configuration to use Room in-memory database
     */
    @Before
    fun init() {
        loadKoinModules(roomTestModule)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @InternalCoroutinesApi
    @Test
    fun insertAndGetTodoList() = runBlockingTest {
        val todoToInsert1 = Todo(id = 1, title = "Titre1")
        val todoToInsert2 = Todo(id = 2, title = "Titre2")
        database.todoDao().saveTodo(todoToInsert1)
        database.todoDao().saveTodo(todoToInsert2)

        val todoFromDatabase = database.todoDao().getTodoList()

        assertEquals(todoFromDatabase.getOrAwaitValue().size, 2)
        assertEquals(todoFromDatabase.getOrAwaitValue()[0], todoToInsert2)
        assertEquals(todoFromDatabase.getOrAwaitValue()[1], todoToInsert1)
    }

    @Test
    fun getTodoWhenNoInserted() = runBlockingTest {
        val todoFromDatabase = database.todoDao().getTodoList()
        assertEquals(todoFromDatabase.getOrAwaitValue().size, 0)
    }

    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}