package com.moringaschool.dogged;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;

import static org.hamcrest.CoreMatchers.allOf;

import android.app.Activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.moringaschool.dogged.Login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginInstrumentedTest {
    @Rule
    public ActivityScenarioRule<LoginActivity>activityScenarioRule=new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    @Test
    public void credentialsValidation(){
        String email="bobbinnej@gmail.com";
        String password="1870691";


    }

}
