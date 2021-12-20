package com.simka.todolistsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.simka.todolistsample.databinding.MainFragmentBinding
import com.simka.todolistsample.model.Todo
import com.simka.todolistsample.ui.dialog.AddTodoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment(), TodoListAdapter.ClickTodoItemListener {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel<MainViewModel>()
    private val todoListAdapter by lazy { TodoListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setupObservers()

        binding.addTodo.setOnClickListener {
            addTodo()
        }
    }

    private fun setupObservers() {
        viewModel.getTodoList().observe(viewLifecycleOwner, Observer { todoList ->
            todoListAdapter.setData(todoList)
        })
    }

    private fun initAdapter() {
        binding.todoRecyclerView.adapter = todoListAdapter
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun addTodo() {
        val dialog = AddTodoFragment()
        dialog.show(childFragmentManager, dialog.tag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun changeTodoStatus(isChecked: Boolean, todo: Todo) {
        viewModel.updateTodoStatus(isChecked, todo)
    }

    override fun selectTodo(todo: Todo) {
        val action = MainFragmentDirections.actionMainToDetail(todo)
        findNavController().navigate(action)
    }


}