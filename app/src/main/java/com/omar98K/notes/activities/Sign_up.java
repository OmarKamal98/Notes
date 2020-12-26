package com.omar98K.notes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.omar98K.notes.R;
import com.omar98K.notes.classes.User;

public class Sign_up extends AppCompatActivity {
   private FirebaseAuth mAuth;
    EditText name,emailEt,passwordEt;
   ProgressBar  progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        progressBar2 =(ProgressBar) findViewById(R.id.progress_bar2);
        emailEt = (EditText) findViewById(R.id.email_edit_text_sign_up);
        passwordEt =(EditText) findViewById(R.id.password_edit_text_sign_up);
        name =(EditText) findViewById(R.id.name_edit_text_sign_up);

    }

    public void singup(View view) {
            registerUser();

        }

        private void registerUser() {
        String fullname=name.getText().toString().trim();
        String email=emailEt.getText().toString().trim();
        String pass=passwordEt.getText().toString().trim();

        if(fullname.isEmpty()){
            name.setError("full name is required !");
            name.requestFocus();
            return;
    }
        if(email.isEmpty()){
            emailEt.setError("email is required !");
            emailEt.requestFocus();
            return;
    }
        if(pass.isEmpty()){
            passwordEt.setError("password is required !");
            passwordEt.requestFocus();
            return;
    }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEt.setError("Please provide valid email !");
            emailEt.requestFocus();
            return;
        }
        if(pass.length() < 6){
            passwordEt.setError("min password length should be 6 characters !");
            passwordEt.requestFocus();
            return;
        }


            progressBar2.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailEt.getText().toString(),passwordEt.getText().toString())
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "New user registration: " + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            progressBar2.setVisibility(View.GONE);
                            Toast.makeText(Sign_up.this, "Registration failed. " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Sign_up.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Sign_up.this, Home_Page.class));
                            Sign_up.this.finish();
                        }
                    }
                });


        }


    public void skipSignUp(View view) {
        finish();
    }

    public void haveAccount(View view) {
        Intent si=new Intent(this,Sign_in.class);
        startActivity(si);
    }
}