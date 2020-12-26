package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.omar98K.notes.R;

public class Confirmation_message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_message);
    }

    public void OnClickcancel(View view) {
        finish();
    }

    public void GoToEmail(View view) {
        Intent i = new Intent(Intent.ACTION_MAIN);
        PackageManager managerclock = getPackageManager();
        i = managerclock.getLaunchIntentForPackage("com.google.android.gm");
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(i);
    }
}