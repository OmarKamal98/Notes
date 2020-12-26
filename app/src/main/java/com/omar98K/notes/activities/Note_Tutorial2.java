package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.omar98K.notes.R;

public class Note_Tutorial2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__tutorial2);
    }

    public void OnClickNextInSecendPage(View view) {
        Intent intent=new Intent(this,Splash.class);
        startActivity(intent);
    }

    public void OnClickSkipInSecendPage(View view) {
        Intent intent=new Intent(this,Splash.class);
        startActivity(intent);
    }
}