package com.example.homework_3.viewmodel

import android.util.Log
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
class NotesViewModel @Inject constructor(private val noteRepo: NoteRepo) : ViewModel() {
    val noteList = noteRepo.getNotes()
    fun getNotes() {
        viewModelScope.launch {
            noteRepo.getNotes().apply {
                noteList
            }

        }
    }


    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepo.delete(note)
        }
    }
}