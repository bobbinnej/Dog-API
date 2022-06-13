package com.moringaschool.dogged.Login;

public class UserObject {

    public String fullName, email;
    //empty user object constructor
    public UserObject(){

    }

    //user object that accepts arguments
    public UserObject(String fullName,String email){
        //initialize
        this.fullName=fullName;
        this.email=email;
    }
}
