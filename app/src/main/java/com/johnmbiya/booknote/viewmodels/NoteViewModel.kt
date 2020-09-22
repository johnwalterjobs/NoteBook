package com.johnmbiya.booknote.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.johnmbiya.booknote.data.AppDataBase
import com.johnmbiya.booknote.data.Note
import com.johnmbiya.booknote.data.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository: NoteRepository

    init {
        val notesDao = AppDataBase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(notesDao)
    }

    fun getAllNotes(): LiveData<List<Note>>? {
        return noteRepository.getNotes()
    }

    fun insertNote(note: Note) {
        noteRepository.addNote(note)
    }

}