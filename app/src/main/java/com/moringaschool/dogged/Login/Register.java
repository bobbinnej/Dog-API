package com.moringaschool.dogged.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
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
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.dogged.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends Activity implements View.OnClickListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.registerUser)
    Button registerUser;
    @BindView(R.id.fullName)
    EditText fullNameEditText;
    @BindView(R.id.signupEmail)
    EditText signupEmailEditText;
    @BindView(R.id.signUpPassword)
    EditText signupPasswordEditText;
    @BindView(R.id.signupProgressBar)
    ProgressBar signupProgressBar;
    boolean passwordVisible;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //change color of edittext inputs to white
        fullNameEditText.setTextColor(Color.parseColor("#FFFFFFFF"));
        signupEmailEditText.setTextColor(Color.parseColor("#FFFFFFFF"));
        signupPasswordEditText.setTextColor(Color.parseColor("#FFFFFFFF"));


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        title.setOnClickListener(this);
        registerUser.setOnClickListener(this);

        // show and hide password using eye icon
        signupPasswordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= signupPasswordEditText.getRight() - signupPasswordEditText.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = signupPasswordEditText.getSelectionEnd();
                        if (passwordVisible) {
                            //set drawable eye icon
                            signupPasswordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            // hide password
                            signupPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;

                        } else {
                            //set drawable eye icon
                            signupPasswordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            // show password
                            signupPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        signupPasswordEditText.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

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
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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
        // set visibility of progress bar to true once you click register
        signupProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
        //check if user has been registered
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UserObject userObject=new UserObject(fullName, email);
                            //send user data to realtime database
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .setValue(userObject).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this,"New user registered successfully!", Toast.LENGTH_LONG).show();
                                                signupProgressBar.setVisibility(View.GONE);


                                            }else{
                                                Toast.makeText(Register.this, "Registration failed! Try again", Toast.LENGTH_LONG).show();
                                                signupProgressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }

                    }
                });


    }
}
