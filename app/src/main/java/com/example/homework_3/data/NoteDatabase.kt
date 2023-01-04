package com.example.homework_3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 5,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion  object {
        fun getNoteDataBaseInstance(context: Context): NoteDatabase {
             return Room.databaseBuilder(
                    context.applicationContext, NoteDatabase::class.java, "note_table"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}