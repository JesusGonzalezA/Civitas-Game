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
public class SorpresaPorCasaHotel extends Sorpresa{
    
    //Atributos
    private int valor;

    //------------------------------------------------------------
    //Métodos
    
    SorpresaPorCasaHotel (String texto,int valor){
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            //Modifico el saldo del jugador en funcion del numero de propiedades
            Jugador j = todos.get(actual);
            j.modificarSaldo(j.cantidadCasasHoteles()*valor);
        }
    }
}
