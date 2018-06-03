package com.abc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.DatabaseHelper;
import Models.Sprite;
import Models.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View view){
        EditText username = findViewById(R.id.username);
        EditText mail = findViewById(R.id.email);

        String name = username.getText().toString();
        String email = mail.getText().toString();

        Matcher matcher = null;
        try{
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(email);
        }catch(Exception e){Toast.makeText(this,"INVALID EMAIL FORMAT", Toast.LENGTH_LONG).show();}

        if(!name.equals("") && matcher.matches()){
            //Add username and email to database
            User user = new User(name,email);
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.addUser(user.getUsername(),user.getEmail(),databaseHelper.getWritableDatabase());
            Sprite userSprite = user.getSprite();
            databaseHelper.addSprite(userSprite.getMaxHealth(),userSprite.getExp(),userSprite.getLevel(),userSprite.getGold(),databaseHelper.getWritableDatabase());
//            //Redirect user to GreetingActivity
//            Intent intent = new Intent(this, GreetingActivity.class);
//            intent.putExtra("USER", user);
//            startActivity(intent);
            startActivity(new Intent(this, GreetingActivity.class));
            finish();
        }
        else{
            Toast.makeText(this,"FILL OUT THE TEXT FIELDS", Toast.LENGTH_LONG).show();
        }
    }
}
