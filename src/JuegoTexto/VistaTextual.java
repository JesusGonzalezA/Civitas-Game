package JuegoTexto;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.Jugador;
import civitas.GestionesInmobiliarias;
import civitas.TituloPropiedad;
import civitas.Respuestas;

class VistaTextual {
  
  private static final int MAX = 100; 
    
  CivitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla ");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        mostrarEstado (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        mostrarEstado (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    mostrarEstado (titulo);
    for (int i = 0; i < lista.size(); i++) {
      mostrarEstado (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
                  new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar() {
    int opcion = menu("¿Desea comprar la calle?", 
                new ArrayList<> (Arrays.asList("No", "Sí")));
    
    return (Respuestas.values()[opcion]);
  }

    void gestionar () {
    
        //Creo menú gestión inmobiliaria
        ArrayList<String> gestiones;
        gestiones = new ArrayList<>(Arrays.asList ("Vender", "Hipotecar", "Cancelar"
                  + " hipoteca", "Construir casa", "Construir Hotel", "Terminar"));

        int opcion = menu("¿Qué gestión desea proceder?",gestiones);

        //Si es terminar no actúo sobre ninguna propiedad
        iPropiedad = -1;   
        iGestion = opcion;
        
        //Si no es terminar, hago menú para actuar sobre una propiedad
        //y actualizaré iPropiedad en función de la elección del actor
        int indiceTerminar = gestiones.indexOf("Terminar");  
        
        if (opcion != indiceTerminar){
            iPropiedad = leeEntero(MAX, "\n\tIntroduce el índice de la propiedad:\t ",
                        "\tEl índice es erróneo");
        }

    }
  
  public int getGestion(){
    return iGestion;
  }
  
  public int getPropiedad(){
    return iPropiedad;
  }
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
    
    //Uso name, porque to_string podría estar sobreescrito
    String salida = "La siguiente operación es: " + operacion.name();
      
    mostrarEstado(salida);
  }


  void mostrarEventos() {
    
    mostrarEstado("\n--> Mostrando eventos pendientes del diario...");
    
    if (!Diario.getInstance().EventosPendientes())
        mostrarEstado("[DIARIO] No hay eventos para mostrar");
    
    else {
        Boolean continuar = true;
        while(continuar){
            mostrarEstado("[DIARIO] " + Diario.getInstance().LeerEvento());
           
            if (!Diario.getInstance().EventosPendientes())
                continuar = false;
        }
    }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
//        this.actualizarVista();

    }
  
  void actualizarVista(){
    
        Jugador jugadorActual = juegoModel.getJugadorActual();
        String titulo = "VISTA ESTADO ACTUAL JUEGO";
        String separador = "\n----------------------------------------------\n";
        String infoJugador = separador; //Usaré actualizar info
        String infoCasilla = separador+juegoModel.getCasillaActual().toString();
        
        //Muestro jugador
        infoJugador += juegoModel.actualizarInfo();
        
        String salida = "\n"+separador+titulo+infoJugador+infoCasilla+separador;
        
        mostrarEstado(salida);
  } 
  
    public static void main (String args[]){
        
        //-------------------------------------------------------------
        //CREO CIVITAS
        //-------------------------------------------------------------
        //Jugadores
        ArrayList<String> nombresJugadores = new ArrayList<>();
        nombresJugadores.add("Jesus");
        nombresJugadores.add("Nuria");
        nombresJugadores.add("Jose");
        nombresJugadores.add("Julio");
        nombresJugadores.add("Javilonguis");

        //Creo Juego
        CivitasJuego Juego = new CivitasJuego(nombresJugadores);
        
        //-------------------------------------------------------------
        //CREO VISTA TEXTUAL
        //-------------------------------------------------------------
        VistaTextual interfaz = new VistaTextual();
        interfaz.setCivitasJuego(Juego);
        
        //Probando métodos de vista textual
        interfaz.mostrarEstado("Probando vista textual.... ");
        interfaz.salirCarcel();
        interfaz.gestionar();
        interfaz.comprar();
        interfaz.mostrarEventos();
        
  }
}


