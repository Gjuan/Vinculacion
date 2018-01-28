package com.vinculacion.app;

import com.vinculacion.app.controllers.AuthController;
import com.vinculacion.app.views.Auth;

public class App {

    public static void main(String[] args) {
        Auth auth = new Auth();
        auth.setVisible(true);
        AuthController ac = new AuthController(auth);
    }
}
