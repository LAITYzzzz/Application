package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Log_In(View view) {
        Intent intent=new Intent(MainActivity.this,LogInActivity.class);
        startActivity(intent);
    }

    public void LogIn_SQLite(View view) {
        Intent intent=new Intent(MainActivity.this,SQLite_Activity.class);
        startActivity(intent);
    }
}