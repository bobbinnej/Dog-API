//package com.moringaschool.dogged.SessionManager;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import com.moringaschool.dogged.Login.LoginActivity;
//import com.moringaschool.dogged.MainActivity;
//
//import java.util.HashMap;
//
//public class LogoutSession {
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//    Context context;
//
//    int Private_mode=0;
//    private static final String PREF_NAME="MyAndroidPref";
//
//    private static final String IS_LOGGEDIN="isLoggedIn";
//    public static final String KEY_EMAIL="email";
//    public static final String KEY_PASSWORD="password";
//
//
//    // constructor
//   public LogoutSession(Context applicationContext){
//      this.context=context;
//      sharedPreferences=context.getSharedPreferences(PREF_NAME,Private_mode);
//      editor= sharedPreferences.edit();
//   }
//   // login session for our app
//   public void createLoginSession(String email, String password){
//      editor.putBoolean(IS_LOGGEDIN, true);
//
//      // add our keys
//      editor.putString(KEY_EMAIL, email);
//      editor.putString(KEY_PASSWORD, password);
//      //commit the changes to editor
//      editor.commit();
//
//   }
//   // check logged in state
//   public boolean isLoggedIn(){
//      return sharedPreferences.getBoolean(IS_LOGGEDIN, false);
//
//   }
//   // if user logs in.. let them be directed to to mainactivity
//   public void checkLogin(){
//      if(!this.isLoggedIn()){
//         Intent intent= new Intent(context, MainActivity.class);
//         //close all activities
//         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//         // flag to start our activity
//         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         //start our login activity
//         context.startActivity(intent);
//      }
//   }
//   // store user data
//   public HashMap<String, String> getUserDetails(){
//      HashMap<String,String> user= new HashMap<String,String>();
//      user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
//      user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
//      return user;
//   }
//
//   // logout function
//   public void logoutUser(){
//      editor.clear();
//      editor.commit();
//      // be directed to logout homepage
//      Intent intent = new Intent(context, LoginActivity.class);
//      //close all our activities
//      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//      context.startActivity(intent);
//
//   }
//}
