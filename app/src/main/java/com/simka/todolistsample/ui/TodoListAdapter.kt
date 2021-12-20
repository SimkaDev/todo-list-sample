package com.simka.todolistsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simka.todolistsample.R
import com.simka.todolistsample.model.Todo


class TodoListAdapter() :
    RecyclerView.Adapter<TodoViewHolder>() {

    private val items = mutableListOf<Todo>()

    fun setData(newItems: List<Todo>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addData(todo: Todo) {
        items.add(todo)
        notifyItemInserted(items.size)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.showData(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }
}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun showData(todo: Todo) = with(itemView) {
        itemView.findViewById<TextView>(R.id.titleTodo).text = todo.title
        itemView.findViewById<CheckBox>(R.id.checkboxTodo).isChecked = todo.isDone
    }
}