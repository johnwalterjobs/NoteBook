package com.johnmbiya.booknote.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.johnmbiya.booknote.R

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)
        findViewById<Button>(R.id.button_save).setOnClickListener(View.OnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(findViewById<EditText>(R.id.edit_word).text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val note = findViewById<EditText>(R.id.edit_word).text.toString()
                replyIntent.putExtra("noteToAdd", note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        })
    }
}