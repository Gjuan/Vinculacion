package com.vinculacion.app;
import com.vinculacion.app.controllers.AuthController;
import com.vinculacion.app.controllers.MenuController;
import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.MenuPrincipal;

public class App {

    public static void main(String[] args) {        
        MenuPrincipal mp = new MenuPrincipal();
        Auth auth = new Auth();        
        AuthController ac = new AuthController(auth, mp);
        auth.setVisible(true);
        
        MenuController mc = new MenuController(mp, auth);
    }
}
