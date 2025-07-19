package com.example.notesmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    ImageView saveButton;
    EditText title, description;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        saveButton = findViewById(R.id.saveButton);
        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    private void addNote() {
        String noteTitle = title.getText().toString().trim();
        String noteDescription = description.getText().toString().trim();


        if (noteTitle.isEmpty() || noteDescription.isEmpty()) {
           title.setError("Please Enter Title");
           description.setError("Please Enter Description");
            Toast.makeText(this, "Please enter both title and description", Toast.LENGTH_SHORT).show();
            return;
        }


        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
        boolean ans = dbhelper.insertNote(noteTitle, noteDescription);

        if (ans) {
            Toast.makeText(this, "Note added successfully!", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();

            finish();
        } else {
            Toast.makeText(this, "Error adding note", Toast.LENGTH_SHORT).show();
        }
    }
}
