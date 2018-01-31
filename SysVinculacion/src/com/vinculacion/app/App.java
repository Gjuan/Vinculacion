package com.vinculacion.app;

import com.vinculacion.app.controllers.AuthController;
import com.vinculacion.app.views.Auth;
import com.vinculacion.app.views.MenuPrincipal;

public class App {

    public static void main(String[] args) {
        Auth auth = new Auth();
        auth.setVisible(true);
        MenuPrincipal mp = new MenuPrincipal();
        AuthController ac = new AuthController(auth, mp);
    }
}
