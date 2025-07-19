package com.example.simpledb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText edtId,edtUsername,edtPassword;
    Button insertBtn,updateBtn,deleteBtn,displayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtId = findViewById(R.id.edtId);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);

        insertBtn = findViewById(R.id.insert);
        updateBtn = findViewById(R.id.update);
        deleteBtn = findViewById(R.id.delete);
        displayBtn = findViewById(R.id.display);


       


        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBhelper ob = new DBhelper(MainActivity.this);
                boolean ans = ob.insert(edtUsername.getText().toString(), edtPassword.getText().toString());
                if(ans == true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Error In Insert ", Toast.LENGTH_SHORT).show();
                }
            }
        });

            displayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(in);
                }
            });


    }
}