package com.abc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }
    public void createDungeon(View view){
        EditText text = findViewById(R.id.goalText);
        String goal = text.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GOAL", goal);

        startActivity(intent);
        finish();
    }
}
