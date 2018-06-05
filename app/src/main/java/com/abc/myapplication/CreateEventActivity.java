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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Database.DatabaseDungeonContract;
import Database.DatabaseDungeonDatesContract;
import Database.DatabaseHelper;
import Database.DatabaseSpriteContract;
import Database.DatabaseUserContract;
import Models.Difficulty;
import Models.Dungeon;
import Models.DungeonDate;
import Models.User;

public class CreateEventActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        setContentView(R.layout.activity_create_event);
    }

    public void createDungeon(View view) {
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

        Date endDate = null;
        String diff = "";
        try {
            diff = button.getText().toString();

            int month = Integer.parseInt(date.substring(0, 2)) - 1;
            int day = Integer.parseInt(date.substring(3, 5));
            int year = Integer.parseInt(date.substring(6, 10));

            Calendar now = Calendar.getInstance();
            int currentYear = now.get(Calendar.YEAR);
            if (year >= currentYear) {
                if (month < 12 && month >= 0) {
                    Calendar days = new GregorianCalendar(year, month, 1);
                    int daysInMonth = days.getActualMaximum(Calendar.DAY_OF_MONTH);
                    if (day <= daysInMonth) {
                        endDate = new Date(year, month, day);
                    }
                }
            }

        } catch (Exception e) {
        }

        if (!goal.equals("") && !diff.equals("") && endDate != null) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Cursor spriteCursor = databaseHelper.readAllSprites(databaseHelper.getReadableDatabase());
            spriteCursor.moveToFirst();
            int maxhealth = spriteCursor.getInt(spriteCursor.getColumnIndex(DatabaseSpriteContract.ContractEntry.MAXHEALTH));
            spriteCursor.close();
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            user.addDungeon(goal, endDate, Difficulty.valueOf(diff));
            Dungeon dungeon = user.getDungeons().get(user.getDungeons().size() - 1);
            Cursor dungeonCursor = databaseHelper.readAllDungeons(databaseHelper.getReadableDatabase());
            int dungeonid = 0;
            if(dungeonCursor.getCount() > 0){
                dungeonCursor.moveToLast();
                dungeonid = dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DUNGEONID)) + 1;
            }
            databaseHelper.addDungeon(dungeonid,goal,maxhealth, maxhealth, diff, penalty,reward, "", "", null, database);

            for (DungeonDate d : dungeon.getDungeonDates()) {
                DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
                String date1 = dateFormat.format(d.getDate());
                Cursor dungeonDateCursor = databaseHelper.readAllDungeonDates(databaseHelper.getReadableDatabase());
                int dateid = 0;
                if(dungeonDateCursor.getCount() != 0){
                    dungeonDateCursor.moveToLast();
                    dateid = dungeonDateCursor.getInt(dungeonDateCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.DATEID)) + 1;
                }
                databaseHelper.addDungeonDate(dateid, dungeonid, dateFormat.format(d.getDate()), d.getStatus().toString(), databaseHelper.getWritableDatabase());
            }
            databaseHelper.close();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "GOAL, DATE, & DIFFICULTY REQUIRED", Toast.LENGTH_LONG).show();
        }
    }
}