package com.omar98K.notes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.omar98K.notes.R;

public class Forgot_password extends AppCompatActivity {
     FirebaseAuth mAuth;

    EditText entEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        entEmail=(EditText) findViewById(R.id.editText5);
    }

    public void onClickrecover(View view) {
        resetPass();
    }
    private void resetPass(){
        String emaill=entEmail.getText().toString().trim();

        if(emaill.isEmpty()){
            entEmail.setError("email is required !");
            entEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
            entEmail.setError("Please provide valid email !");
            entEmail.requestFocus();
            return;
        }
       mAuth.sendPasswordResetEmail(emaill).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   Toast.makeText(Forgot_password.this,"Check you email",Toast.LENGTH_LONG).show();
               }
           }
       });



    }

    public void backFromForgetPass(View view) {
      finish();
    }
}