package com.simka.todolistsample.di

import androidx.room.Room
import com.simka.todolistsample.database.TodoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            TodoDatabase::class.java,
            "my_database")
            .createFromAsset("database/todo.db")
            .build()
    }
}