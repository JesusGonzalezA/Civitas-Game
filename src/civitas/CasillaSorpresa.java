/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class CasillaSorpresa extends Casilla{
    
    //---------------------------------------------
    //Atributos
    private Sorpresa sorpresa;
    private MazoSorpresas mazo;
    //---------------------------------------------
    //Métodos
    
    CasillaSorpresa (MazoSorpresas mazo, String nombre ) {
        super(nombre);
        this.mazo = mazo;
    }
    
    //-----------------------------
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos ){
        if (jugadorCorrecto(actual,todos)){
            //1
            Sorpresa sorpresa = mazo.siguiente();
            
            //2
            super.recibeJugador(actual, todos);
            
            //3
            sorpresa.aplicarAJugador(actual, todos);
        }
    }
    
    //-----------------------------
    @Override
    public String toString(){   
        String representacion = super.toString();
        
        if (sorpresa != null)
                representacion += "\t- Sorpresa = " + sorpresa.toString() + "\n";
        
        return representacion;
    }
}
