package com.example.brainscript.repository

interface BaseRepository<T> {
    suspend fun insert(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
}