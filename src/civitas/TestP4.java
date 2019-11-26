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
public class TestP4 {
    
    public static void main (String args[]){
        
        //Declaracion jugador y titulo de propiedad
        Jugador j1 = new Jugador("Jesus");
        TituloPropiedad Murcia = new TituloPropiedad("Murcia",
                                        25.0f,50.0f,1000.0f,2000.0f,500.0f);
        
        
        //Comprar casilla
        j1.puedeComprarCasilla();
        j1.comprar(Murcia);
        Murcia.actualizaPropietarioPorConversion(j1);
        
        //Mostrar que la transacción ha sido un éxito
        System.out.println(j1.toString());
        System.out.println(Murcia.toString());
        j1.pagaImpuesto(200);
        
        //Cambiar a jugador especulador
        SorpresaConvertir convertir = new SorpresaConvertir(200);
        ArrayList<Jugador> todos = new ArrayList<>();
        todos.add(j1);
        convertir.aplicarAJugador(0, todos);
        
        //Muestro el propietario
        System.out.println(Murcia.getPropietario().toString());
        
        todos.get(0).pagaImpuesto(200);
        
        todos.get(0).encarcelar(0);
        
        
        //Leer diario
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t-" + Diario.getInstance().LeerEvento() );
        
    }
}
