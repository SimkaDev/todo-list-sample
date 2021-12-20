package com.simka.todolistsample.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.simka.todolistsample.R
import com.simka.todolistsample.databinding.DialogAddTodoBinding

class AddTodoFragment : DialogFragment() {

    private var _binding: DialogAddTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTodoViewModel by viewModel<AddTodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewModel.todoSaved.observe(viewLifecycleOwner, { isSaved ->
            if (isSaved) dismiss()
        })
        viewModel.savedError.observe(viewLifecycleOwner, { errorOccured ->
            if (errorOccured) {
                binding.newTodoTitleInput.error = getString(R.string.field_error)
            } else  {
                binding.newTodoTitleInput.error = null
            }
        })
    }


    private fun initListeners() {
        binding.saveTodoAction.setOnClickListener { saveTodo()}
        binding.newTodoTitleInput.addTextChangedListener {
            viewModel.titleTodo = it.toString()
        }
        binding.descriptionInput.addTextChangedListener {
            viewModel.descriptionTodo = it.toString()
        }
    }

    private fun saveTodo() {
        viewModel.saveTodo()
    }

}