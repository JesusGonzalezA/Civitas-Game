/*
Esta clase sólo tiene main porque será la que use para probar el juego,
la vista y el controlador
*/

package JuegoTexto;

import civitas.CivitasJuego;
import civitas.Dado;
//import JuegoTexto.VistaTextual
//import JuegoTexto.Controlador



import java.util.ArrayList;
public class ProgramaPrincipal {
    
    
    public static void main (String args[]){
        
        //Creo jugadores
        ArrayList<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Jesus");
        nombresJugadores.add("Nuria");
        nombresJugadores.add("Jose");
        nombresJugadores.add("Julio");
        nombresJugadores.add("Javilonguis");
        
        //Creo juego en modo debug
        CivitasJuego juego = new CivitasJuego(nombresJugadores);
        Dado.getInstance().setDebug(true);
        
        //Jugar
        VistaTextual interfaz = new VistaTextual();
        Controlador controlador = new Controlador(juego,interfaz);
        controlador.juega();
        
        
        
    }
}

