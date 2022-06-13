package com.moringaschool.dogged.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.dogged.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends Activity implements View.OnClickListener {
    @BindView(R.id.title) TextView title;
    @BindView(R.id.registerUser) Button registerUser;
    @BindView(R.id.fullName) EditText fullNameEditText;
    @BindView(R.id.signupEmail)  EditText signupEmailEditText;
    @BindView(R.id.signUpPassword) EditText signupPasswordEditText;
    @BindView(R.id.signupProgressBar)
    ProgressBar signupProgressBar;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        title.setOnClickListener(this);
        registerUser.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title:
                startActivity(new Intent(this,LoginActivity.class ));
                break;
            case R.id.registerUser:
                registerUser();
                break;

        }

    }

    private void registerUser() {
        // validation of signup inputs
        String fullName=fullNameEditText.getText().toString().trim();
        String email=signupEmailEditText.getText().toString().trim();
        String password=signupPasswordEditText.getText().toString().trim();

        if(fullName.isEmpty()){
            fullNameEditText.setError("FullName is required");
            fullNameEditText.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            signupEmailEditText.setError("Email is required");
            signupEmailEditText.requestFocus();
            return;
        }
        // to ensure email matches correct format
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signupEmailEditText.setError("This is not a valid email structure");
            signupEmailEditText.requestFocus();
            return;
        }



        if(password.isEmpty()){
            signupPasswordEditText.setError("Password is required");
            signupPasswordEditText.requestFocus();
            return;
        }
        //to ensure password is not less than 6 characters
        if(password.length()<6){
            signupPasswordEditText.setError("Min password length is 6 characters");
            signupPasswordEditText.requestFocus();
            return;
        }
    }
}
