package com.moringaschool.dogged.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.dogged.MainActivity;
import com.moringaschool.dogged.R;
//import com.moringaschool.dogged.SessionManager.LogoutSession;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends Activity implements  View.OnClickListener {
    private static final String FILE_NAME = "myFile";
    @BindView(R.id.signUp) TextView signup;
    @BindView(R.id.emailLogin) EditText emailLoginEditText;
    @BindView(R.id.passwordLogin) EditText passwordLoginEditText;
    @BindView(R.id.signIn) Button signIn;
    @BindView(R.id.loginProgressBar) ProgressBar loginProgressBar;
    @BindView(R.id.forgotPassword) TextView forgotPassword;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.checkBoxRemember) CheckBox rememberMe;

    private AlertDialog alertDialog;
    boolean passwordVisible;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // set color of edittext inputs to white
        emailLoginEditText.setTextColor(Color.parseColor("#FFFFFFFF"));
        passwordLoginEditText.setTextColor(Color.parseColor("#FFFFFFFF"));

        SharedPreferences sharedPreferences=getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        String email= sharedPreferences.getString("email","");
        String password=sharedPreferences.getString("password","");
        emailLoginEditText.setText(email);
        passwordLoginEditText.setText(password);
        
        // initialize mAuth
        mAuth=FirebaseAuth.getInstance();

        // add an onclick listener to the signup text view
        signIn.setOnClickListener(this);
        signup.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        title.setOnClickListener(this);

        //show and hide password using the eye icon
        passwordLoginEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
              final int Right=2;
              if(event.getAction()==MotionEvent.ACTION_UP){
                  if(event.getRawX()>=passwordLoginEditText.getRight()-passwordLoginEditText.getCompoundDrawables()[Right].getBounds().width()){
                      int selection=passwordLoginEditText.getSelectionEnd();
                      if(passwordVisible){
                          //set drawable eye icon
                          passwordLoginEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                          // hide password
                          passwordLoginEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                          passwordVisible=false;

                      }else{
                          //set drawable eye icon
                          passwordLoginEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
                          // show password
                          passwordLoginEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                          passwordVisible=true;
                      }
                      passwordLoginEditText.setSelection(selection);
                      return true;
                  }
              }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signUp:
                startActivity(new Intent(this, Register.class));
                finish();
                break;
            case R.id.signIn:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                finish();
                break;
            case R.id.title:
                startActivity(new Intent(this, Register.class));
                finish();
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
        // checkbox functionality
        if(rememberMe.isChecked()){
            storeDataUsingSharedPreference(email, password);
        }
        loginProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Logging you in....");
                    builder.setTitle("Success");
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
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();


                    }else{
                        Toast.makeText(LoginActivity.this, "Check email to verify your account", Toast.LENGTH_LONG).show();

                    }

                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login! Check your Credentials!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    //remember me functionality
    private void storeDataUsingSharedPreference(String email, String password) {
        SharedPreferences.Editor editor=getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.apply();
    }
}