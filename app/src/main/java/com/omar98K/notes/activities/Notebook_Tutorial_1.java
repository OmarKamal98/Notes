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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook__tutorial_1);

    }

    public void onClickSkip1(View view) {

        Intent intent=new Intent(this,Splash.class);
        startActivity(intent);
    }

    public void OnClickNextInFirstPage(View view) {

        Intent intent=new Intent(this,Note_Tutorial2.class);
        startActivity(intent);
    }
}