package com.example.springbootdemoconsumer.Email;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class Email_Authenticator extends Authenticator {
    String UserLoginName=null;
    String UserPassword=null;
    public Email_Authenticator(){

    }
    public Email_Authenticator(String UserLoginName,String UserPassword){
        this.UserLoginName=UserLoginName;
        this.UserPassword=UserPassword;
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(UserLoginName,UserPassword);
    }

}
