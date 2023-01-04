package com.example.homework_3.repository

import androidx.lifecycle.LiveData
import com.example.homework_3.data.Note
import com.example.homework_3.data.NoteDao
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepo {
    override fun getNotes(): LiveData<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getId(id: Int): Note? {
        return dao.getId(id)
    }

    override suspend fun insert(note: Note) {
        dao.insert(note)
    }

    override suspend fun delete(note: Note) {
        dao.delete(note)
    }
}
