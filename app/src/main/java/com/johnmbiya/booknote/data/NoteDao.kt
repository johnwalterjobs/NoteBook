package com.johnmbiya.booknote.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)

}