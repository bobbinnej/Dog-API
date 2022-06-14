package com.moringaschool.dogged.SessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.moringaschool.dogged.MainActivity;

public class LogoutSession {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    int Private_mode=0;
    private static final String PREF_NAME="MyAndroidPref";

    private static final String IS_LOGGEDIN="isLoggedIn";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";


    // constructor
   public LogoutSession(){
      this.context=context;
      sharedPreferences=context.getSharedPreferences(PREF_NAME,Private_mode);
      editor= sharedPreferences.edit();
   }
   // login session for our app
   public void createLoginSession(String email, String password){
      editor.putBoolean(IS_LOGGEDIN, true);

      // add our keys
      editor.putString(KEY_EMAIL, email);
      editor.putString(KEY_PASSWORD, password);
      //commit the changes to editor
      editor.commit();

   }
   // check logged in state
   public boolean isLoggedIn(){
      return sharedPreferences.getBoolean(IS_LOGGEDIN, false);

   }
   // if user logs in.. let them be directed to to mainactivity
   public void checkLogin(){
      if(!this.isLoggedIn()){
         Intent intent= new Intent(context, MainActivity.class);
         //close all activities
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         // flag to start our activity
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         //start our login activity
         context.startActivity(intent);

      }
   }
}
