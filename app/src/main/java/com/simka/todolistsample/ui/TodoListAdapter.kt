package com.simka.todolistsample.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simka.todolistsample.R
import com.simka.todolistsample.model.Todo


class TodoListAdapter(val listener: TodoListAdapter.CheckboxListener) :
    RecyclerView.Adapter<TodoViewHolder>() {

    interface CheckboxListener {
        fun changeTodoStatus(isChecked: Boolean, todo: Todo)
    }

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

        holder.itemView.findViewById<CheckBox>(R.id.checkboxTodo).setOnCheckedChangeListener { _, isChecked ->
            listener.changeTodoStatus(isChecked, items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }
}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun showData(todo: Todo) = with(itemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTodo)
        titleTextView.text = todo.title
        itemView.findViewById<CheckBox>(R.id.checkboxTodo).isChecked = todo.isDone
        if (todo.isDone) {
            titleTextView.paintFlags = titleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            titleTextView.paintFlags = titleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}