package com.simka.todolistsample

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.simka.todolistsample.di.roomTestModule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import java.lang.Thread.sleep


class MainActivityTest: KoinTest {

    /**
     * Override default Koin configuration to use Room in-memory database and mock api
     */
    @Before
    fun init() {
       loadKoinModules(listOf(roomTestModule))
    }

    @Test
    fun loadsActivityWithNoTodo() {
        ActivityScenario.launch(MainActivity::class.java)

        sleep(1000)
        onView(withId(R.id.todoRecyclerView)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            assertEquals(0, recyclerView.adapter?.itemCount)
        }
    }

}