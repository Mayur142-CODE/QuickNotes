package com.example.notesmvvm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "allnotes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_CONTENT + " TEXT NOT NULL"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNote(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);

        long res = db.insert(TABLE_NAME, null, values);
        db.close();
        if(res==-1)
        {
            return false;
        }
        return true;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
               @SuppressLint("Range") int Id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                notes.add(new Note(Id,title, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public Note getNoteById(int noteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT},
                COLUMN_ID + "=?", new String[]{String.valueOf(noteId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
            cursor.close();
            db.close();
            return new Note(id, title, content); // Return the note object
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null; // Return null if the note isn't found
    }

    public boolean updateNote(int id, String title, String content) {
        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a ContentValues object to store the updated values
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);       // Update the title
        values.put(COLUMN_CONTENT, content);   // Update the content

        // Update the note in the database where the ID matches
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});

        db.close();  // Close the database connection

        // Return true if one or more rows were updated, otherwise false
        return rowsAffected > 0;
    }

    public boolean deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected > 0; // Returns true if the note was deleted
    }
}
