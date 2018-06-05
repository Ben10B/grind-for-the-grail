package com.abc.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Date;

import Database.DatabaseHelper;
import Models.Difficulty;
import Models.Dungeon;
import Models.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        this.user = databaseHelper.generateUserFromDatabase();
        databaseHelper.close();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        createTestDungeons();
        addUserDungeons();
//        userDungeons();
    }

    private void userDungeons() {
        LinearLayout linearLayout = findViewById(R.id.dungeonContainer);
        Intent intent = getIntent();

        // Create Button Dynamically at Runtime
        Button btnShow = new Button(this);
        btnShow.setText("Dynamic Dungeon");
        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Dungeon Screen", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        Button btnShow2 = new Button(this);
        String goal = intent.getStringExtra("GOAL");
        btnShow2.setText("Dungeon: " +goal);
        btnShow2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Dungeon Screen", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        // Add Button to LinearLayout
        if (linearLayout != null) {
            linearLayout.addView(btnShow);
            if(goal != null){
                linearLayout.addView(btnShow2);
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_account){
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }else if(id == R.id.nav_modelInfo){
            startActivity(new Intent(this, FoggModelActivity.class));
        }
        else if(id == R.id.credits){
            startActivity(new Intent(this, CreditsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void createEvent(View view){
        Intent intent = new Intent(this, CreateEventActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void createTestDungeons(){
        user.addDungeon("Test Dungeon 1", new Date(2018,9,26), Difficulty.Squire);
        user.addDungeon("Test Dungeon 2", new Date(2018,5,7), Difficulty.Knight);
    }

    private void addUserDungeons(){
        LinearLayout linearLayout = findViewById(R.id.dungeonContainer);
        Intent intent = getIntent();
        for(Dungeon d : user.getDungeons()){
            final Button btnShow = new Button(this);
            btnShow.setText(d.getTitle());
            btnShow.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            btnShow.setTextColor(getResources().getColor(R.color.white));
            btnShow.setPadding(60,20,60,20);
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,20,0,20);
            btnShow.setLayoutParams(lp);
         //   btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent send = new Intent(MainActivity.this, EventActivity.class);
                    send.putExtra("user", user);
                    send.putExtra("dungeonTitle", btnShow.getText());
                    startActivity(send);
                }
            });
            linearLayout.addView(btnShow);
        }
        // Create Button Dynamically at Runtime

        final Button btnShow2 = new Button(this);
        String goal = intent.getStringExtra("GOAL");
        btnShow2.setText(goal);
        btnShow2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Dungeon Screen", Toast.LENGTH_LONG).show();
                Intent send = new Intent(MainActivity.this, EventActivity.class);
                send.putExtra("user", user);
                send.putExtra("dungeonTitle", btnShow2.getText());
                startActivity(send);
            }
        });

        // Add Button to LinearLayout
        if (linearLayout != null) {
//            linearLayout.addView(btnShow);
            if(goal != null){
                linearLayout.addView(btnShow2);
            }
        }
    }

    private void loadUser(){
        //DatabaseHelper dbh = new DatabaseHelper();
    }
}
