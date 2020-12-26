package com.omar98K.notes.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.omar98K.notes.R;

import java.util.Arrays;
import java.util.List;

public class Sign_in extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailEt,passwordEt;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth=FirebaseAuth.getInstance();
        progressBar =(ProgressBar) findViewById(R.id.progress_bar);
        emailEt = (EditText) findViewById( R.id.edit_text_email_login);
        passwordEt =  (EditText) findViewById(R.id.edit_text_password_login);

    }

    public void home(View view) {
        Intent ss = new Intent(this, Splash.class);
        startActivity(ss);
    }



    public void forget(View view) {
        Intent forget = new Intent(this, Forgot_password.class);
        startActivity(forget);
    }

    public void onClickSignIn(View view) {
        login();
    }

    public void sighup(View view) {
        Intent sign1=new Intent(this,Sign_up.class);
        startActivity(sign1);
    }



    private void login() {

        String email = emailEt.getText().toString().trim();
        String pass = passwordEt.getText().toString().trim();


        if (email.isEmpty()) {
            emailEt.setError("email is required !");
            emailEt.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            passwordEt.setError("password is required !");
            passwordEt.requestFocus();
            return;
        }
        if (pass.length()< 6) {
            passwordEt.setError("min password length should be 6 characters !");
            passwordEt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Please provide valid email !");
            emailEt.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Sign_in.this, "login Successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Sign_in.this,Home_Page.class);
                    startActivity(intent);
                    Sign_in.this.finish();
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Sign_in.this, "login failed", Toast.LENGTH_LONG).show();

                }

            }
        }
        );
    }
}