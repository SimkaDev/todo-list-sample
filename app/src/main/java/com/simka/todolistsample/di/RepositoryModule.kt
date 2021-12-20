package com.simka.todolistsample.di

import com.simka.todolistsample.repository.TodoRepository
import com.simka.todolistsample.repository.TodoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { TodoRepositoryImpl(get()) as TodoRepository }
}