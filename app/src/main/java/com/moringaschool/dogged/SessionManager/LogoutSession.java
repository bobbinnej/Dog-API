package com.moringaschool.dogged.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

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
}
