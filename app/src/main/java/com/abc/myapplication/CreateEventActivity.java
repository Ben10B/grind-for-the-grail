package com.abc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        databaseHelper.addDungeon(4, 7, diff, penalty,reward, "sex", "grreat sex", "null", );

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GOAL", goal);

        startActivity(intent);
        finish();
    }
}
