package com.abc.myapplication;

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

public class EventActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

//        viewPager = findViewById(R.id.pager);
//        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//
//        tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
        int j = 1;
        for(int i = 1; i <= 31; i++) {
            addButtons("May " + i + " 2018", j);
            j += 3;
        }

    }

    private void addButtons(String date, int row_id) {
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
                    Toast.makeText(EventActivity.this, R.string.yes_btn_clicked_text, Toast.LENGTH_LONG).show();
        //          String snackBarText = getResources().getString(R.string.yes_btn_clicked_text);
//                  LinearLayout snackBarLayout = findViewById(R.id.snackbar);
//                  Snackbar mySnackbar = Snackbar.make(snackBarLayout, snackBarText, Snackbar.LENGTH_SHORT);

                }
            }
        });
    }
}
