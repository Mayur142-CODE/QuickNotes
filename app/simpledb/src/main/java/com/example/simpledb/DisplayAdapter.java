package com.example.simpledb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class DisplayAdapter extends BaseAdapter {

    Context c;
    List<User> users;
    LayoutInflater inflater;

    DisplayAdapter(Context c, List<User> users)
    {
    this.c = c;
    this.users = users;
    inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.activity_list,parent,false);

        TextView usernameTextView = row.findViewById(R.id.textUsername);
        TextView passwordTextView = row.findViewById(R.id.textPassword);
        TextView id = row.findViewById(R.id.textID);
        User user = users.get(position);
        usernameTextView.setText(user.getUsername());
        passwordTextView.setText(user.getPassword());
        id.setText(""+user.getId());
        return row;

    }
}

