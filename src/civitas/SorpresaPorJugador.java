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
public class SorpresaPorJugador extends Sorpresa{
    
    //Atributos
    private int valor;

    //------------------------------------------------------------
    //Métodos
    
    SorpresaPorJugador (String texto,int valor){
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            super.aplicarAJugador(actual, todos);
            //Todos los jugadores pagan al jugador actual
            String textoPagar = "Pagas "+ valor +" a "+todos.get(actual).getNombre();
            String textoCobrar = "Recibes " + valor + " de cada jugador";
            int numJugadores = todos.size();
            
            Sorpresa pagar = new SorpresaPagarCobrar(textoPagar,-valor);
            Sorpresa cobro = new SorpresaPagarCobrar(textoCobrar,(numJugadores-1)*valor);
            //Aplico pagos
           
            for (int i=0; i<numJugadores; ++i){
                if (i != actual)
                    pagar.aplicarAJugador(i,todos);
            }
            
            //Aplico cobro
            cobro.aplicarAJugador(actual, todos);
            
        }
    }
}
