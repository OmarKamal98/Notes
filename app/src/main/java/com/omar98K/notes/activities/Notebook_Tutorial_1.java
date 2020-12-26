package com.omar98K.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.omar98K.notes.R;

public class Notebook_Tutorial_1 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook__tutorial_1);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    //Change UI according to user data.
    public void updateUI(FirebaseUser account){

        if(account != null){

            startActivity(new Intent(this,Home_Page.class));

        }

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