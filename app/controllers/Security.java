package controllers;

public class Security extends Secure.Security {
    static boolean authenticate(String username,String password){
        return true;
    }
}
