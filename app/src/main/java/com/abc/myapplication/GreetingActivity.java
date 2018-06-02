package com.abc.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Database.DatabaseHelper;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        Cursor cursor = dbhelper.readAllUsers(dbhelper.getReadableDatabase());
        if(cursor.getCount() >= 1){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_greeting);
        } else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }

    }
    public void goToDashboard(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
