package ir.navaco.behnoudnote.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE Id = :noteId")
    fun getNote(noteId: String): LiveData<Note>

    @Insert
    fun insertNote(note: Note)

}