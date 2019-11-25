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
public class SorpresaIrCarcel extends Sorpresa{
    
    //Atributos
    private int valor;
    private Tablero tablero;
    
    //-------------------------------------------------------------------
    //Métodos
    
    SorpresaIrCarcel(Tablero tablero){
        super("Ve a la Cárcel");
        this.tablero = tablero;
        valor = tablero.getCarcel();
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            super.aplicarAJugador(actual, todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
}
