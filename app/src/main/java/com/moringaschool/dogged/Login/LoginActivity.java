package com.moringaschool.dogged.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.dogged.R;

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

        // add an onclick listener to the signup text view
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signUp:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.signIn:

        }
    }
}

