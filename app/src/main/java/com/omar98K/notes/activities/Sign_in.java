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
   //private static final int RC_SIGN_IN = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth=FirebaseAuth.getInstance();
        progressBar =(ProgressBar) findViewById(R.id.progress_bar);
        emailEt = (EditText) findViewById( R.id.edit_text_email_login);
        passwordEt =  (EditText) findViewById(R.id.edit_text_password_login);

    }
    /*
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
   public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
 //               new AuthUI.IdpConfig.PhoneBuilder().build(),
 //          new AuthUI.IdpConfig.GoogleBuilder().build()
//                new AuthUI.IdpConfig.FacebookBuilder().build()
//                new AuthUI.IdpConfig.TwitterBuilder().build()
        );

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

            }
        }
    }
*/
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