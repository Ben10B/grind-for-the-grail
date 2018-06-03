package com.abc.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import Models.Difficulty;
import Models.Dungeon;
import Models.DungeonDate;
import Models.User;

public class EventActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    Date currentDate = Calendar.getInstance().getTime();
    private User user;
    private Dungeon dungeon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        String dungeonTitle = intent.getStringExtra("dungeonTitle");
        Toast.makeText(this, dungeonTitle, Toast.LENGTH_SHORT).show();
        dungeon = user.getDungeonByTitle(dungeonTitle);
        setContentView(R.layout.activity_event);
        Calendar incrementedDate = Calendar.getInstance();

//        viewPager = findViewById(R.id.pager);
//        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//
//        tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);

//        DungeonDate[] dates = dungeon.getDungeonDates();
        int id = 1;
//        for (int i = 1; i < dates.length; i++) {
        for(int i = 1; i <= 31; i++) {
            incrementedDate.add(Calendar.DATE, 1);
            Date printedDate = incrementedDate.getTime();
//            Date printedDate = dates[i].getDate();
            if(i > 1) {
                addButtons(printedDate + "", id, "yet");
            }else{
                addButtons(printedDate+"", id, "came");
            }
            id += 3;
        }

    }

    private void addButtons(String date, int row_id, String has) {
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
        noBtn.setBackgroundColor(Color.RED);
//        noBtn.setBackgroundResource(R.drawable.benlogo);
        noBtn.setTextColor(Color.WHITE);
        noBtn.setTypeface(null, Typeface.BOLD);
        noBtn.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //Text Date
        TextView textView = new TextView(this);
        textView.setText(date);
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
        yesBtn.setBackgroundColor(Color.GREEN);
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
                    tv.setBackgroundColor(Color.RED);
                    tv.setTextColor(Color.BLACK);
                    Button b = findViewById(id + 2);
                    b.setBackgroundColor(Color.argb(50, 88, 88, 88));
                    b.setTextColor(Color.argb(50, 0, 0, 0));
                    Toast.makeText(EventActivity.this, R.string.no_btn_clicked_text, Toast.LENGTH_SHORT).show();
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
                    tv.setBackgroundColor(Color.GREEN);
                    tv.setTextColor(Color.BLACK);
                    Button b = findViewById(id-2);
                    b.setBackgroundColor(Color.argb(50, 88,88,88));
                    b.setTextColor(Color.argb(50, 0,0,0));
                    Difficulty difficulty = dungeon.getDifficulty();
//                    dungeon.completeDay( ,user.getSprite(), );


                    Toast.makeText(EventActivity.this, R.string.yes_btn_clicked_text, Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(has.equals("yet")){
            noBtn.setClickable(false);
            yesBtn.setClickable(false);
            noBtn.setBackgroundColor(Color.argb(50, 88,88,88));
            yesBtn.setBackgroundColor(Color.argb(50, 88,88,88));
            textView.setBackgroundColor(Color.argb(50, 88,88,88));
        }
    }
}
