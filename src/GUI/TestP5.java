/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.CivitasView;
import GUI.Dado;

/**
 *
 * @author jesus
 */
public class TestP5 {
    
    public static void main (String args[]){
        
        //Creo la vista
        CivitasView vista = new CivitasView();
        
        //Creo una instancia de dado, al que indico la interfaz donde 
        //se mostrará
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(Boolean.TRUE);
    }
}
