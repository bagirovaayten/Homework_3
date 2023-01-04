package com.example.homework_3.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getId(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

}