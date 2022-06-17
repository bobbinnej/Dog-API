package com.moringaschool.dogged.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.dogged.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity {
    @BindView(R.id.emailForgot)
    EditText emailForgotEditText;
    @BindView(R.id.resetPassword)
    Button resetpassword;
    @BindView(R.id.forgotProgressBar)
    ProgressBar forgotProgressBar;
    @BindView(R.id.title)
    TextView title;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        // change color of input in email edit text
        emailForgotEditText.setTextColor(Color.parseColor("#FFFFFFFF"));
        // add an onclick listener to title

        auth=FirebaseAuth.getInstance();
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                resetPassword();
            }
        });


    }



    private void resetPassword() {
        // validation of inputs
        String email=emailForgotEditText.getText().toString().trim();
        if(email.isEmpty()){
            emailForgotEditText.setError("Email is required");
            emailForgotEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailForgotEditText.setError("This is not a valid email format");
            emailForgotEditText.requestFocus();
            return;
        }
        //set progress bar visibility to visible
        forgotProgressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Proceed to your email to reset your password", Toast.LENGTH_LONG).show();
                    forgotProgressBar.setVisibility(View.GONE);


                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "Something went wrong! Please Try again later", Toast.LENGTH_LONG).show();
                    forgotProgressBar.setVisibility(View.GONE);
                }
            }
        });


    }
}