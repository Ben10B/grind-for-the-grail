package com.abc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Models.Dungeon;
import Models.User;

public class LevelUpActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        TextView levelUpTextView = findViewById(R.id.level_up_textView);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        levelUpTextView.setText("YOU ARE NOW LEVEL " + user.getSprite().getLevel());
    }
}
