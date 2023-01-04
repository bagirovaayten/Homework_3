package com.example.homework_3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_3.data.Note
import com.example.homework_3.repository.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNotesViewModel @Inject constructor(private val repo: NoteRepo) : ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note


    fun addNote(note: Note) {
        viewModelScope.launch {
            repo.insert(note)
        }
    }
}