package controllers;

import com.google.gson.Gson;
import play.mvc.Controller;
import play.mvc.With;

//@With(Secure.class)
public class Admin extends Controller {


    public static void ccc(){
        System.err.println("---- hello ----");
        String a = "aaaa";
        render(a);
    }

}
