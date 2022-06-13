package com.moringaschool.dogged.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.dogged.MainActivity;
import com.moringaschool.dogged.R;
import com.moringaschool.dogged.RandomBreed;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements  View.OnClickListener {
    @BindView(R.id.signUp) TextView signup;
    @BindView(R.id.emailLogin) EditText emailLoginEditText;
    @BindView(R.id.passwordLogin) EditText passwordLoginEditText;
    @BindView(R.id.signIn) Button signIn;
    @BindView(R.id.loginProgressBar) ProgressBar loginProgressBar;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //change color of edittext inputs to white
        emailLoginEditText.setTextColor(Color.parseColor("#FFFFFFFF"));
        passwordLoginEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

        // initialize mAuth
        mAuth=FirebaseAuth.getInstance();

        // add an onclick listener to the signup text view
        signIn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signUp:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.signIn:
                userLogin();
                break;

        }
    }

    private void userLogin() {
        // validation
        String email=emailLoginEditText.getText().toString().trim();
        String password= passwordLoginEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailLoginEditText.setError("Email is required");
            emailLoginEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailLoginEditText.setError("Input a valid email format");
            emailLoginEditText.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            passwordLoginEditText.setError("Password is required");
            passwordLoginEditText.requestFocus();
            return;
        }
        if(password.length()<6){
            passwordLoginEditText.setError("Min password length is 6 characters!");
            passwordLoginEditText.requestFocus();
            return;
        }
        loginProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // redirect to splashscreen
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login! Check your Credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}