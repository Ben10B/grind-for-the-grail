package com.abc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View view){
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        String name = username.getText().toString();
        String pass = password.getText().toString();

        if(!name.equals("") && !pass.equals("")){
            //Add username and password to database

            //Redirect user to GreetingActivity
            startActivity(new Intent(this, GreetingActivity.class));
            finish();
        }
        else{
            Toast.makeText(this,"FILL OUT THE TEXT FIELDS", Toast.LENGTH_LONG).show();
        }
    }
}
