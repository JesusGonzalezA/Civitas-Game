/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;


public class CasillaCalle extends Casilla{
    
    
    //---------------------------------------------------------
    //Atributos
    private TituloPropiedad tituloPropiedad;
    //---------------------------------------------------------
    //Métodos
    
    CasillaCalle (TituloPropiedad titulo){
        super(titulo.getNombre());
        tituloPropiedad = titulo;
    }
    
    //-----------------------------
    
    TituloPropiedad getTituloPropiedad (){
        return tituloPropiedad;
    }
    
    //-----------------------------
    
    @Override
    void recibeJugador (int actual, ArrayList<Jugador> todos ){
        
        if (jugadorCorrecto(actual,todos)){
            super.recibeJugador(actual, todos);
            
            if (!tituloPropiedad.tienePropietario() )
                todos.get(actual).puedeComprarCasilla();    
            else  
                tituloPropiedad.tramitarAlquiler(todos.get(actual));    
        }
    }
    
    //-----------------------------
    
    @Override
    public String toString(){

        String representacion = "";
        
        representacion += super.toString();
        
        if (tituloPropiedad != null)
                representacion += "\t- Titulo de propiedad = " + tituloPropiedad.toString() + "\n";
        
        return representacion;
    }
}
