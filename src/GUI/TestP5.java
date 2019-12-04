/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.CivitasView;
import GUI.Dado;
import civitas.CivitasJuego;
import civitas.Jugador;
import civitas.TituloPropiedad;
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
        //Instanciacion
        
        //  Vista
        //  |
        //  -> Dado
        //  -> Capturador
        //     |
        //     -> Juego
        //        |
        //        -> Controlador
        
            //Vista
        CivitasView vista = new CivitasView();
            //Dado
        Dado.createInstance(vista);
            //Capturador
        CapturaNombres capturador = new CapturaNombres(vista,Boolean.TRUE);
            //Juego
        ArrayList<String> nombres = capturador.getNombres();
        CivitasJuego juego = new CivitasJuego(nombres);
            //Controlador
        Controlador controlador = new Controlador(juego,vista);
        
        
        //Pongo el modo debug
        Dado.getInstance().setDebug(Boolean.TRUE);
        
        //Hilar el juego con la vista
        vista.setCivitasJuego(juego);
        
        //***************************************************************
        
        //---------------------------------------------------------------
        //FLUJO DEL JUEGO
        //---------------------------------------------------------------
        controlador.juega();
        //***************************************************************
    }
}
