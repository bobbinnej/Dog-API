package com.moringaschool.dogged.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.tv.TvInputService;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.dogged.MainActivity;
import com.moringaschool.dogged.R;
import com.moringaschool.dogged.RandomBreed;
//import com.moringaschool.dogged.SessionManager.LogoutSession;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements  View.OnClickListener {
    @BindView(R.id.signUp) TextView signup;
    @BindView(R.id.emailLogin) EditText emailLoginEditText;
    @BindView(R.id.passwordLogin) EditText passwordLoginEditText;
    @BindView(R.id.signIn) Button signIn;
    @BindView(R.id.loginProgressBar) ProgressBar loginProgressBar;

    private AlertDialog alertDialog;

//    LogoutSession session;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);




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
                    AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Login successful.....");
                    builder.setTitle("Login");
                    builder.setCancelable(false);

                    // check if email is verified or not
                    FirebaseUser mUser= FirebaseAuth.getInstance().getCurrentUser();
                    if(mUser.isEmailVerified()){
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // redirect to splashscreen
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                                dialog.cancel();
                            }
                        });

                    }else{
                        mUser.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check email to verify your account", Toast.LENGTH_LONG).show();
                    }
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login! Check your Credentials!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}