package com.simka.todolistsample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.simka.todolistsample.model.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM todo order by isDone asc, id desc")
    fun getTodoList(): LiveData<List<Todo>>

}