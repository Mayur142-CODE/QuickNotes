package com.example.notesmvvm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    private List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra("noteId", note.getId());
                ((AppCompatActivity) v.getContext()).startActivityForResult(intent, 2);
            }
        });

        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(v.getContext());
                boolean isDeleted = dbHelper.deleteNote(note.getId());
                if (isDeleted) {
                    noteList.remove(position); // Remove note from list
                    notifyItemRemoved(position); // Notify adapter about item removal
                    Toast.makeText(v.getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Error deleting note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }




    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView UpdateButton,DeleteButton;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            description = itemView.findViewById(R.id.contentTextView);
            UpdateButton = itemView.findViewById(R.id.updateButton);
            DeleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
