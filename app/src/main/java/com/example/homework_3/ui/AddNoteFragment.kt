package com.example.homework_3.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework_3.R
import com.example.homework_3.data.Note
import com.example.homework_3.databinding.FragmentAddNoteBinding
import com.example.homework_3.viewmodel.AddNotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    lateinit var binding: FragmentAddNoteBinding
    lateinit var viewModel: AddNotesViewModel
    var id: Int? = null
    val args: AddNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddNotesViewModel::class.java]
        Log.i("id", "${args.id}")
        if (args.id != 0) {
            viewModel.getNoteById(args.id)
        }
        setText()
        setClickListeners()
    }

    private fun setText() {
        viewModel.note.observe(viewLifecycleOwner) {
            binding.title.text.append(it.title)
            binding.body.text.append(it.body)
            id = it.id
        }
    }

    private fun setClickListeners() {
        binding.btn.setOnClickListener {
            if (binding.title.text.toString().isNotEmpty()) {
                viewModel.addNote(
                    Note(
                        title = binding.title.text.toString(),
                        body = binding.body.text.toString()
                    )
                )
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please add title", Toast.LENGTH_SHORT).show()
            }
        }
    }
}