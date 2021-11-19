package com.example.vuelveaqui.util;

public class Validations {

    public boolean passEquals(String pass1, String pass2){
        if(pass1.equals(pass2)){
            return true;
        }else{
            return false;
        }
    }

    public boolean passMore5Caracter(String pass1){
        if(pass1.length()>5){
            return true;
        }else{
            return false;
        }
    }
}
