package com.abc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import Database.DatabaseHelper;
import Models.Sprite;
import Models.User;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        User user = (User)intent.getSerializableExtra("user");
        databaseHelper.close();
        Sprite sprite = user.getSprite();

        String userExp = user.getSprite().getExp()+"";
        TextView expTextView = findViewById(R.id.profile_exp_textview);
        expTextView.setText(userExp);

        String userLvl = user.getSprite().getLevel()+"";
        TextView lvlTextView = findViewById(R.id.profile_level_textview);
        lvlTextView.setText(userLvl);

        String userGold = user.getSprite().getGold()+"";
        TextView goldTextView = findViewById(R.id.profile_gold_textview);
        goldTextView.setText(userGold);

        String userEmail = user.getEmail();
        TextView emailTextView = findViewById(R.id.profile_email_textview);
        emailTextView.setText(userEmail);

        String userName = user.getUsername();
        TextView userNameTextView = findViewById(R.id.profile_username_textview);
        userNameTextView.setText((userName));

    }
}
