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
public class CasillaImpuesto extends Casilla{
    
    //-------------------------------------------------------------
    //Atributos
    private float importe;
    //-------------------------------------------------------------
    //Métodos
   
    CasillaImpuesto (String nombre , float cantidad){
        super(nombre);
        importe = cantidad;
    }
    
    //-----------------------------
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
        
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual, todos);
            
            //Jugador paga impuesto
            todos.get(actual).pagaImpuesto(importe);
        }
    }
}
