package com.abc.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

import Database.DatabaseHelper;
import Models.Difficulty;
import Models.Dungeon;

public class CreateEventActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }
    public void createDungeon(View view){
        EditText gT = findViewById(R.id.goalText);
        EditText dT = findViewById(R.id.dateText);
        EditText rT = findViewById(R.id.rewardText);
        EditText pT = findViewById(R.id.punishmentText);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton button = findViewById(radioId);

        String goal = gT.getText().toString();
        String date = dT.getText().toString();
        String reward = rT.getText().toString();
        String penalty = pT.getText().toString();
        String diff = button.getText().toString();

        int year = Integer.parseInt(date.substring(0, 3));
        int month = Integer.parseInt(date.substring(5, 6));
        int day = Integer.parseInt(date.substring(8, 9));

        Date date1 = new Date(year, month, day);
        Dungeon newDungeon = new Dungeon(goal, 7, date1, "grreat sex", reward, "sex", penalty, Difficulty.None, false);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        databaseHelper.addDungeon(4, 7, diff, penalty,reward, "sex", "grreat sex", "OFF", database);
        databaseHelper.close();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GOAL", goal);
//        if(goal.length() == 0){
            startActivity(intent);
            finish();
//        }
    }
}
