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
public class SorpresaIrCasilla extends Sorpresa{
    
    //Atributos
    private Tablero tablero;
    private int valor;
    
    //-------------------------------------------------------
    //Métodos
    
    SorpresaIrCasilla (Tablero tablero, int valor, String texto){
        super(texto);
        this.tablero = tablero;
        this.valor = valor;
    }
     
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        
        if (jugadorCorrecto(actual, todos)){
            super.aplicarAJugador(actual, todos);
           //Ir a casilla
            
                //Calcular posicion en el tablero por si pasa por salida
           int casillaActual = (todos.get(actual).getNumCasillaActual());
           int tirada = tablero.calcularTirada(casillaActual, valor);
           casillaActual = tablero.nuevaPosicion (casillaActual, tirada);
            
                //Mover a nueva posicion del tablero
           todos.get(actual).moverACasilla(casillaActual);
            
                //La casilla recibe al jugador
           tablero.getCasilla(casillaActual).recibeJugador(actual, todos);
        }
    }
}
