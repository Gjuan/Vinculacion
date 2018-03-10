package com.vinculacion.app.controllers;
/**
 *
 * @author jorge
 */
public class ValidatorController {
    
    public static boolean validationCedula(String cedula) {
        boolean validate = false;
        int sumPares = 0;
        int mulImpar = 0;   
        int sumImpares = 0;
        int sumParImpar = 0;
        int decenaSuperior = 0;
    
        char [] arrayCedula = cedula.toCharArray();
        for (int i = 1; i < 9; i += 2) {
            sumPares = sumPares + Integer.parseInt(String.valueOf(arrayCedula[i]));
        }
        for (int i = 0; i < 9; i += 2) {
            mulImpar = (Integer.parseInt(String.valueOf(arrayCedula[i]))) * 2;
            if (mulImpar > 9) {
                mulImpar = ((Integer.parseInt(String.valueOf(arrayCedula[i]))) * 2) - 9;            
            }
            sumImpares = sumImpares + mulImpar;
        }
        
        sumParImpar = sumPares + sumImpares;
        decenaSuperior = 10 - (sumParImpar % 10);
        if (decenaSuperior > 9) {
            decenaSuperior = 0;
        }
        if (Integer.parseInt(String.valueOf(arrayCedula[9])) == decenaSuperior) {
            validate = true;
        }else{
            validate = false;
        }       
        
        return validate;
    }
}
