package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.omar98K.notes.R;

public class Splash extends AppCompatActivity {
   /* private SharedPreferences preferences;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    public void signup_splash(View view) {
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

    public void signin_splash(View view) {
        Intent intent = new Intent(this, Sign_in.class);
        startActivity(intent);
    }
}