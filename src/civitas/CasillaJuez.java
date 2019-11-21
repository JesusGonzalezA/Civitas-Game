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
public class CasillaJuez extends Casilla{
    
    //--------------------------------------------------------------
    //Atributos
    private static int carcel = -1;
    //--------------------------------------------------------------
    //Métodos
    
    CasillaJuez (String nombre, int numCasillaCarcel){
        super(nombre);
        carcel = numCasillaCarcel;
    }
    
    //----------------------------
    
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
       
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual, todos);
            
            //Encarcela al jugador
            todos.get(actual).encarcelar(carcel);
        }
    }
}
