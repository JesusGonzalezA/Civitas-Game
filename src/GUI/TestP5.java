/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.CivitasView;
import GUI.Dado;
import civitas.CivitasJuego;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class TestP5 {
    
    public static void main (String args[]){
        
        //--------------------------------------------------------------
        //DECLARACION DE VARIABLES Y PREPARACION
        //--------------------------------------------------------------
        //Creo la vista
        CivitasView vista = new CivitasView();
        
        //Creo una instancia de dado, al que indico la interfaz donde 
        //se mostrará
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(Boolean.TRUE);
        
        //Creo una instancia de captura nombres
        CapturaNombres capturador = new CapturaNombres(vista,Boolean.TRUE);
        ArrayList<String> nombres = new ArrayList<>();
        nombres = capturador.getNombres();
        
        //Crear una instancia del juego
        CivitasJuego juego = new CivitasJuego(nombres);
        
        //Crear el controlador
        Controlador controlador = new Controlador(juego,vista);
        
        //Hilar el juego con la vista
        vista.setCivitasJuego(juego);
        
        //***************************************************************
        
        //---------------------------------------------------------------
        //---------------------------------------------------------------
        //***************************************************************
    }
}
