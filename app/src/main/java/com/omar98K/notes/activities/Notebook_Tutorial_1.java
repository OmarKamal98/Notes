package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.omar98K.notes.R;

public class Notebook_Tutorial_1 extends AppCompatActivity {
   /* Button btn_next;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook__tutorial_1);
      /*  FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        preferences = getSharedPreferences("Prefs", MODE_PRIVATE);
        if (!preferences.getBoolean("is_first_time", true)) {

            // Go to splash activity (user logged in)
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        btn_next = findViewById(R.id.btn_next);*/
    }

    public void onClickSkip1(View view) {
        /*editor = preferences.edit();
        editor.putBoolean("is_first_time", false);
        Toast.makeText(this, preferences.getBoolean("is_first_time",true)+"", Toast.LENGTH_SHORT).show();
        editor.apply();*/
        Intent intent=new Intent(this,Splash.class);
        startActivity(intent);
    }

    public void OnClickNextInFirstPage(View view) {
       /* editor = preferences.edit();
        editor.putBoolean("is_first_time", false);
        editor.apply();*/
        Intent intent=new Intent(this,Note_Tutorial2.class);
        startActivity(intent);
    }
}