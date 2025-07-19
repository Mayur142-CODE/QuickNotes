    package com.example.notesmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity {

    FloatingActionButton addButton;
    RecyclerView noteRecycler;
    NoteAdapter noteAdapter;
    List<Note> noteList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteRecycler = findViewById(R.id.notesRecyclerview);
        noteRecycler.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(noteList);
        noteRecycler.setAdapter(noteAdapter);

        loadNotes();



        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,AddNoteActivity.class);
                startActivityForResult(in, 1);

            }
        });
    }

        private void loadNotes() {

            DatabaseHelper dbhelper = new DatabaseHelper(this);
            noteList = dbhelper.getAllNotes();

            noteAdapter = new NoteAdapter(noteList);
            noteRecycler.setAdapter(noteAdapter);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if (requestCode == 1 && resultCode == RESULT_OK) {

                loadNotes();
            }
            else if (requestCode == 2 && resultCode == RESULT_OK) {
                loadNotes();
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }