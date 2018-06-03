package com.abc.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import Database.DatabaseHelper;
import Models.User;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        Cursor cursor = dbhelper.readAllUsers(dbhelper.getReadableDatabase());
//        Intent intent = getIntent();
        if(cursor.getCount() >= 1){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_greeting);
        } else {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_register);
            startActivity(new Intent(this, RegisterActivity.class));
            finish();

    }
    }
    public void goToDashboard(View view){
        Toast.makeText(this, "ij", Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(GreetingActivity.this, MainActivity.class);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
