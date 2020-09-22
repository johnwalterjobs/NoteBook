package com.johnmbiya.booknote.data

import androidx.lifecycle.LiveData
import com.johnmbiya.booknote.data.NoteDao
import kotlinx.coroutines.runBlocking

class NoteRepository(private val noteDao: NoteDao) {

    fun getNotes(): LiveData<List<Note>> {
        return noteDao.getNotes()
    }
    fun addNote(note: Note) = runBlocking {
        noteDao.insertNote(note)
    }

}