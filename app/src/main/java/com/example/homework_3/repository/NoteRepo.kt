package com.example.homework_3.repository

import androidx.lifecycle.LiveData
import com.example.homework_3.data.Note

interface NoteRepo {

    fun getNotes(): LiveData<List<Note>>

    suspend fun getId(id: Int): Note?

    suspend fun delete(note: Note)

    suspend fun insert(note: Note)


}