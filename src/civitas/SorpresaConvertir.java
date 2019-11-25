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
public class SorpresaConvertir extends Sorpresa{
    
    //Atributos
    private float fianza;    
    
    //---------------------------------------------------------
    //Métodos
    SorpresaConvertir(float fianza){
        super("Convertir a jugador especulador");
        this.fianza = fianza;
    }
    
     @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            super.aplicarAJugador(actual, todos);
            todos.set(actual, new JugadorEspeculador(todos.get(actual),fianza));
        }
    }
}
