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
public class SorpresaPagarCobrar extends Sorpresa{
    
    //Atributos
    private int valor;

    //------------------------------------------------------------
    //Métodos
    
    SorpresaPagarCobrar (String texto,int valor){
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            super.aplicarAJugador(actual, todos);
            //Modifico el saldo del jugador
            todos.get(actual).modificarSaldo(valor);
        }
    }
}
