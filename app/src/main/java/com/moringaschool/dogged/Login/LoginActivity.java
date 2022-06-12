package com.moringaschool.dogged.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moringaschool.dogged.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {
    @BindView(R.id.signUp) TextView signup;

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
              startActivity(new Intent(this,Register.class));
              break;
      }
    }
}