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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_3.R
import com.example.homework_3.databinding.FragmentMainBinding
import com.example.homework_3.ui.adapter.NotesAdapter
import com.example.homework_3.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: NotesViewModel
    private val adapter by lazy { NotesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setAdapter()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addNoteFragment)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        viewModel.getNotes()
        viewModel.noteList.observe(viewLifecycleOwner) { t ->
            adapter.setData(t)
        }
    }

    private fun setAdapter() {
        binding.recylerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recylerview.adapter = adapter
        adapter.onDeleteItemClick = {
            adapter.onDeleteItemClick = { note ->
                viewModel.deleteNote(note)
                Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}