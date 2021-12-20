package com.simka.todolistsample.di

import com.simka.todolistsample.ui.MainViewModel
import com.simka.todolistsample.ui.detail.TodoDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { TodoDetailViewModel() }
}