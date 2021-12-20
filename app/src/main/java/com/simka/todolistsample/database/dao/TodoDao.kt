package com.simka.todolistsample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.simka.todolistsample.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveTodo(todo: Todo)

    @Query("SELECT * FROM todo order by id desc, isDone desc")
    fun getTodoList(): LiveData<List<Todo>>
}