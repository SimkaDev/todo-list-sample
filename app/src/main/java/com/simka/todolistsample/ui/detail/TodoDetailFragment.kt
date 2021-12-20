package com.simka.todolistsample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.simka.todolistsample.databinding.TodoDetailFragmentBinding
import com.simka.todolistsample.model.Todo
import org.koin.androidx.viewmodel.ext.android.viewModel

class TodoDetailFragment: Fragment() {

    private var _binding: TodoDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoDetailViewModel by viewModel<TodoDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = TodoDetailFragmentArgs.fromBundle(it)
            val todo : Todo = safeArgs.selectedTodo
            viewModel.setTodo(todo)
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.todoDetailsLiveData.observe(viewLifecycleOwner, Observer { todoDetail ->
            binding.titleTextView.text = todoDetail.title
            binding.descriptionTextView.text = todoDetail.description
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}