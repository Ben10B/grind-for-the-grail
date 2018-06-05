package com.abc.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VictoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        TextView victoryTextview = findViewById(R.id.victory_textview);
        victoryTextview.setBackgroundColor(Color.argb(50,0,0,0));
    }
}
