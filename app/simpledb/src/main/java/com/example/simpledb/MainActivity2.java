package com.example.simpledb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ListView DisplayView;
    List<User> userList;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DisplayView = findViewById(R.id.displayView);
        userList = new ArrayList<>();
        loadData();
        
    }

    private void loadData() {

        userList.clear();
        DBhelper dbhelper = new DBhelper(MainActivity2.this);
        List<User> users = dbhelper.getAllUsers();
        userList.addAll(users);


        DisplayAdapter ob = new DisplayAdapter(this,userList);
        DisplayView.setAdapter(ob);
    }

}