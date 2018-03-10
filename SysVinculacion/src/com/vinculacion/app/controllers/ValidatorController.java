package com.vinculacion.app.controllers;
/**
 *
 * @author jorge
 */
public class ValidatorController {
    
    private boolean validate = false;
    private int sumPares = 0;
    private int mulImpar = 0;   
    private int sumImpares = 0;
    private int sumParImpar = 0;
    private int decenaSuperior = 0;
    
    public  boolean validationCedula(String cedula) {
        char [] arrayCedula = cedula.toCharArray();
        for (int i = 1; i < 9; i += 2) {
            this.sumPares = sumPares + Integer.parseInt(String.valueOf(arrayCedula[i]));
        }
        for (int i = 0; i < 9; i += 2) {
            this.mulImpar = (Integer.parseInt(String.valueOf(arrayCedula[i]))) * 2;
            if (this.mulImpar > 9) {
                this.mulImpar = ((Integer.parseInt(String.valueOf(arrayCedula[i]))) * 2) - 9;            
            }
            this.sumImpares = sumImpares + mulImpar;
        }
        
        this.sumParImpar = this.sumPares + this.sumImpares;
        this.decenaSuperior = 10 - (this.sumParImpar % 10);
        if (this.decenaSuperior > 9) {
            this.decenaSuperior = 0;
        }
        if (Integer.parseInt(String.valueOf(arrayCedula[9])) == this.decenaSuperior) {
            this.validate = true;
        }else{
            this.validate = false;
        }        
        return validate;
    }
}
