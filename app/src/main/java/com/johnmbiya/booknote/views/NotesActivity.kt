package com.johnmbiya.booknote.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.johnmbiya.booknote.R
import com.johnmbiya.booknote.data.Note

import com.johnmbiya.booknote.data.NotesListAdapter
import com.johnmbiya.booknote.viewmodels.NoteViewModel

class NotesActivity : AppCompatActivity() {
    private val newWordActivityRequestCode = 1

    private lateinit var viewModel_Note: NoteViewModel

    var adapter: NotesListAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        viewModel_Note = ViewModelProvider(this).get(NoteViewModel::class.java)

        initRecyclerView()

        viewModel_Note!!.getAllNotes()?.observe(this, Observer { notes: List<Note> ->
            adapter?.setNotes(notes)
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@NotesActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    fun initRecyclerView() {

        adapter = NotesListAdapter(this)
        findViewById<RecyclerView>(R.id.recyclerview).layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        findViewById<RecyclerView>(R.id.recyclerview).adapter = adapter

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra("noteToAdd")?.let {
                val note = Note(it)
                viewModel_Note?.insertNote(note)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}