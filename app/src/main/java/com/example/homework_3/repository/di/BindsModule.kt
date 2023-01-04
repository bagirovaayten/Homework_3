package com.example.homework_3.repository.di

import com.example.homework_3.repository.NoteRepo
import com.example.homework_3.repository.NoteRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {
    @Singleton
    @Binds
    abstract fun bindDepositRepository(repo: NoteRepoImpl): NoteRepo
}