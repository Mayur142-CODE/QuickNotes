package com.example.notesmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    ImageView updateButton;
    EditText updatetitle, updatedescription;
    DatabaseHelper dbhelper;
    int noteId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateSaveButton);
        updatetitle = findViewById(R.id.updateTitleEditText);
        updatedescription = findViewById(R.id.updateDescriptionEditText);

        dbhelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);

        Note note = dbhelper.getNoteById(noteId);
        if (note != null) {
            updatetitle.setText(note.getTitle());
            updatedescription.setText(note.getDescription());
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedTitle = updatetitle.getText().toString();
                String updatedDescription = updatedescription.getText().toString();

                if (!updatedTitle.isEmpty() && !updatedDescription.isEmpty()) {

                    boolean isUpdated = dbhelper.updateNote(noteId, updatedTitle, updatedDescription);
                    if (isUpdated) {
                        // Set result OK and finish the activity
                        Intent resultIntent = new Intent();
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {

                        Toast.makeText(UpdateActivity.this, "error in update", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });





    }
}