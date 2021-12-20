package com.simka.todolistsample.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.simka.todolistsample.database.TodoDatabase
import org.koin.dsl.module

val roomTestModule = module(override = true) {
    single {
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }
}