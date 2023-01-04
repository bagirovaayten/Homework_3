package com.example.homework_3.repository.di

import android.app.Application
import com.example.homework_3.data.Note
import com.example.homework_3.data.NoteDao
import com.example.homework_3.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.annotation.Nullable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {


    @Singleton
    @Provides
    fun getRoomDbInstance(context: Application): NoteDatabase {
        return NoteDatabase.getNoteDataBaseInstance(context)
    }
    @Provides
    @Singleton
    fun getUserDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }
    @Provides
    fun provideEntity() = Note()



}