package com.abc.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Database.DatabaseDungeonContract;
import Database.DatabaseHelper;
import Database.DatabaseSpriteContract;
import Database.DatabaseUserContract;
import Models.Difficulty;
import Models.Dungeon;
import Models.DungeonDate;
import Models.User;

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

        Date date1 = null;
        String diff = "";
        try{
            diff = button.getText().toString();
            int year = Integer.parseInt(date.substring(0, 3));
            int month = Integer.parseInt(date.substring(5, 6));
            int day = Integer.parseInt(date.substring(8, 9));

            date1 = new Date(year, month, day);
        } catch(Exception e){}

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor spriteCursor = databaseHelper.readAllSprites(databaseHelper.getReadableDatabase());
        spriteCursor.moveToFirst();
        int maxhealth = spriteCursor.getInt(spriteCursor.getColumnIndex(DatabaseSpriteContract.ContractEntry.MAXHEALTH));
        spriteCursor.close();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Dungeon dungeon = new Dungeon(goal,maxhealth,date1,Difficulty.valueOf(diff));
        databaseHelper.addDungeon(goal,maxhealth, maxhealth, diff, penalty,reward, "", "", null, database);
        Cursor dungeonCursor = databaseHelper.readAllDungeons(databaseHelper.getReadableDatabase());
        int dungeonid = 0;
        if (dungeonCursor.moveToFirst()) {
            while (!dungeonCursor.isAfterLast()) {
                if(dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.NAME)) == goal &&
                   dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DIFFICULTY)) == diff &&
                   dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.MAXHEALTH)) == maxhealth)
                    dungeonid = dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DUNGEONID));
                dungeonCursor.moveToNext();
            }
        }

        for (DungeonDate d : dungeon.getDungeonDates()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            databaseHelper.addDungeonDate(dungeonid,dateFormat.format(d.getDate()),d.getStatus().toString(),databaseHelper.getWritableDatabase());
        }
        databaseHelper.close();

        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("GOAL", goal);

//        if(goal.length() == 0){
            startActivity(intent);
            finish();
//        }
    }
}
