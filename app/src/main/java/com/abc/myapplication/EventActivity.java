package com.abc.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Database.DatabaseDungeonContract;
import Database.DatabaseDungeonDatesContract;
import Database.DatabaseHelper;
import Database.DatabaseSpriteContract;
import Models.Difficulty;
import Models.Dungeon;
import Models.DungeonDate;
import Models.Status;
import Models.User;

public class EventActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    Date currentDate = Calendar.getInstance().getTime();
    private User user;
    private Dungeon dungeon;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        String dungeonTitle = intent.getStringExtra("dungeonTitle");
        dungeon = user.getDungeonByTitle(dungeonTitle);
        setContentView(R.layout.activity_event);


        //Progress Bar Dynamic Stuff
        progressBar = findViewById(R.id.dungeon_health_progress_bar);
        progressBar.setMax(dungeon.getMaxHealth());
        progressBar.setProgress(dungeon.getCurrentHealth());

        dungeon.updateDungeonDates();
        ArrayList<DungeonDate> dates = dungeon.getDungeonDates();
        int id = 1;
        for (int i = 0; i < dates.size(); i++) {
            DungeonDate temp = dates.get(i);
            Date printedDate = temp.getDate();

            if(currentDate.after(printedDate)){
                UpdateWithStatus(printedDate.toString(), Status.Unresolved);
            }
            addButtons(printedDate, id, temp.getStatus());
            id += 3;
        }
    }

    private void addButtons(final Date date, int row_id, Status status) {
        //Row Container
        TableRow tableRow = new TableRow(this);
        tableRow.setGravity(Gravity.CENTER);
        tableRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tableRow.getLayoutParams();
        p.setMargins(0, 50, 0, 0);

        //No button
        final Button noBtn = new Button(this);
        noBtn.setText("NO");
        noBtn.setId(row_id);
        noBtn.setBackgroundColor(getResources().getColor(R.color.MichaelRed));
        //noBtn.setBackgroundResource(R.drawable.treasure);
        noBtn.setTextColor(Color.WHITE);
        noBtn.setTypeface(null, Typeface.BOLD);
        noBtn.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //Text Date
        TextView textView = new TextView(this);
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        textView.setText(dateFormat.format(date));
        textView.setId((row_id+1));
        textView.setEms(10);
        textView.setBackgroundColor(Color.BLACK);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //Yes button
        final Button yesBtn = new Button(this);
        yesBtn.setText("YES");
        yesBtn.setId((row_id+2));
        yesBtn.setBackgroundColor(getResources().getColor(R.color.MichaelGreen));
        yesBtn.setTextColor(Color.WHITE);
        yesBtn.setTypeface(null, Typeface.BOLD);
        yesBtn.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));



        // Add Button to Row Container
        tableRow.addView(noBtn);
        tableRow.addView(textView);
        tableRow.addView(yesBtn);

        // Add row to LinearLayout Container
        LinearLayout ll = findViewById(R.id.buttonContainer);
        ll.setPadding(0,0,0,50);
        ll.addView(tableRow);

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noBtn.getCurrentTextColor() != Color.argb(50, 0,0,0)) {
                    noBtn.setBackgroundColor(Color.argb(50, 88, 88, 88));
                    noBtn.setTextColor(Color.argb(50, 0, 0, 0));
                    int id = noBtn.getId();
                    TextView tv = findViewById(id + 1);
                    tv.setBackgroundColor(getResources().getColor(R.color.MichaelRed));
                    tv.setTextColor(getResources().getColor(R.color.white));
                    Button b = findViewById(id + 2);
                    b.setBackgroundColor(Color.argb(50, 88, 88, 88));
                    b.setTextColor(Color.argb(50, 0, 0, 0));

                    Difficulty difficulty = dungeon.getDifficulty();
                    String failMessage = dungeon.failDay(date);
                    DatabaseHelper databaseHelper = new DatabaseHelper(EventActivity.this);
                    Cursor dungeonCursor = databaseHelper.readAllDungeons(databaseHelper.getReadableDatabase());
                    if (dungeonCursor.moveToFirst()) {
                        while (!dungeonCursor.isAfterLast()) {
                            if(dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.NAME)) == dungeon.getTitle()){
                                databaseHelper.updateDungeon(dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DUNGEONID)),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.NAME)),
                                        dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.MAXHEALTH)),
                                        dungeon.getCurrentHealth(),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DIFFICULTY)),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.REGULARPENALTY)),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.REGULARREWARD)),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.ULTIMATEFAILURE)),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.ULTIMATEREWARD)),
                                        dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.HEROMODE)),
                                        databaseHelper.getWritableDatabase());
                            }

                            dungeonCursor.moveToNext();
                        }
                    }
                    String date = tv.getText().toString();
                    UpdateWithStatus(date,Status.Failed);
                    progressBar.setProgress(dungeon.getCurrentHealth());
                    Toast.makeText(EventActivity.this, failMessage, Toast.LENGTH_SHORT).show();

                }
            }
        });
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yesBtn.getCurrentTextColor() != Color.argb(50, 0,0,0)) {
                    yesBtn.setBackgroundColor(Color.argb(50, 88,88,88));
                    yesBtn.setTextColor(Color.argb(50, 0,0,0));
                    int id = yesBtn.getId();
                    TextView tv = findViewById(id-1);
                    tv.setBackgroundColor(getResources().getColor(R.color.MichaelGreen));
                    tv.setTextColor(getResources().getColor(R.color.white));
                    Button b = findViewById(id-2);
                    b.setBackgroundColor(Color.argb(50, 88,88,88));
                    b.setTextColor(Color.argb(50, 0,0,0));
                    Difficulty difficulty = dungeon.getDifficulty();
                    String rewardTitle = dungeon.completeDay(date ,user.getSprite());

                    DatabaseHelper databaseHelper = new DatabaseHelper(EventActivity.this);
                    Cursor spriteCursor = databaseHelper.readAllSprites(databaseHelper.getReadableDatabase());
                    spriteCursor.moveToFirst();

                    databaseHelper.updateSprite(1,
                            user.getSprite().getMaxHealth(),user.getSprite().getExp(),user.getSprite().getLevel(), user.getSprite().getGold(),
                            databaseHelper.getWritableDatabase());
                    spriteCursor.close();
                    databaseHelper.close();
                    String date = tv.getText().toString();
                    UpdateWithStatus(date,Status.Completed);
                    Toast.makeText(EventActivity.this, rewardTitle, Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(status == Status.Inactive){
            noBtn.setClickable(false);
            yesBtn.setClickable(false);
            noBtn.setBackgroundColor(Color.argb(50, 88,88,88));
            yesBtn.setBackgroundColor(Color.argb(50, 88,88,88));
            textView.setBackgroundColor(Color.argb(50, 88,88,88));
        }
        else if(status == Status.Completed){
            noBtn.setBackgroundColor(Color.argb(50, 88,88,88));
            noBtn.setTextColor(Color.argb(50, 0,0,0));
            textView.setBackgroundColor(Color.GREEN);
            textView.setTextColor(Color.BLACK);
            yesBtn.setBackgroundColor(Color.argb(50, 88,88,88));
            yesBtn.setTextColor(Color.argb(50, 0,0,0));
        }
        else if(status == Status.Failed){
            noBtn.setBackgroundColor(Color.argb(50, 88, 88, 88));
            noBtn.setTextColor(Color.argb(50, 0, 0, 0));
            textView.setBackgroundColor(Color.RED);
            textView.setTextColor(Color.BLACK);
            yesBtn.setBackgroundColor(Color.argb(50, 88, 88, 88));
            yesBtn.setTextColor(Color.argb(50, 0, 0, 0));
        }
//        if(currentDate.after(date) && status == Status.Inactive){
//            UpdateWithStatus(textView.getText().toString(), Status.Unresolved);
//        }
    }
    private void UpdateWithStatus(String date,Status status){
        DatabaseHelper databaseHelper = new DatabaseHelper(EventActivity.this);
        Cursor dungeonCursor = databaseHelper.readAllDungeons(databaseHelper.getReadableDatabase());
        int dungeonid = -1;
        if (dungeonCursor.moveToFirst()) {
            while (!dungeonCursor.isAfterLast()) {
                String d = dungeonCursor.getString(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.NAME));
                String e = dungeon.getTitle();
                if( d.equals(e) )
                    dungeonid =  dungeonCursor.getInt(dungeonCursor.getColumnIndex(DatabaseDungeonContract.ContractEntry.DUNGEONID));
                dungeonCursor.moveToNext();
            }
        }
        Cursor dungeonDatesCursor = databaseHelper.readDungeonDatesByDungeon(dungeonid,databaseHelper.getReadableDatabase());
        if (dungeonDatesCursor.moveToFirst()) {
            while (!dungeonDatesCursor.isAfterLast()) {
                String x = date;
                String a = dungeonDatesCursor.getString(dungeonDatesCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.DATE));
                if(dungeonDatesCursor.getString(dungeonDatesCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.DATE)).equals(date))
                    databaseHelper.updateDungeonDates(dungeonDatesCursor.getInt(dungeonDatesCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.DATEID)),
                            dungeonid,
                            dungeonDatesCursor.getString(dungeonDatesCursor.getColumnIndex(DatabaseDungeonDatesContract.ContractEntry.DATE)),
                            status.toString(),databaseHelper.getWritableDatabase());
                dungeonDatesCursor.moveToNext();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_dungeon) {

            return true;
        }
        return true;
    }
}
