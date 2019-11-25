package civitas;

//import civitas.Tablero;
//import civitas.MazoSorpresas;
//import civitas.TipoSorpresa;

import java.util.ArrayList;


abstract public class Sorpresa {
    
    //-------------------------------------------------------------
    //Atributos de referencia
    private MazoSorpresas mazo;
    
    //Atributos de instancia
    private String texto;
    private int valor;
    
    
    //-------------------------------------------------------------
    //Métodos
    
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual,todos))
            informe(actual,todos);
    }
    
    private void informe (int actual, ArrayList<Jugador> todos){
        String evento = "Se esta aplicando la sorpresa [" +this.toString()
                    + "] al jugador " + todos.get(actual).getNombre();
        Diario.getInstance().OcurreEvento(evento);
    }
    
    public Boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        return (todos.size()>actual) && (actual>=0);
    }
    
    Sorpresa (String texto){
        this.texto = texto; 
    }
    
    @Override
    public String toString () {
        return texto;
    }
    
    //-------------------------------------------------------------
    
    public static void main (String args[]){
        
        //Pruebo constructores
        
            //Creo Casilla
        TituloPropiedad TituloPaseoDelPrado = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        Casilla PaseoDelPrado = new CasillaCalle (TituloPaseoDelPrado);
            
            //Creo tablero correcto
        Tablero tablero = new Tablero (1);
        tablero.añadeCasilla(PaseoDelPrado);
        tablero.añadeJuez();
        
            //Creo Mazo
        MazoSorpresas mazo1 = new MazoSorpresas();
        
        System.out.println("*Probando constructores....");
        SorpresaSalirCarcel libreCarcel = new SorpresaSalirCarcel(mazo1); 
        SorpresaIrCasilla irAPaseoDelPrado = new SorpresaIrCasilla (tablero,1,"Ir a Paseo del Prado");
        SorpresaIrCarcel veCarcel = new SorpresaIrCarcel (tablero);
        SorpresaPorCasaHotel impuestoPropiedades = new SorpresaPorCasaHotel ("Paga por propiedades",50);
        SorpresaPagarCobrar paga = new SorpresaPagarCobrar ("Paga",-50);
        SorpresaPorJugador pagaTodos = new SorpresaPorJugador ("Paga a todos/Cobra 1",50);
       
            //Añado al mazo la sorpresa libre carcel
        mazo1.alMazo(libreCarcel);
        
        
        //Probando to string
        System.out.println("*Probando to string...");
        System.out.println("\t1-" + libreCarcel.toString());
        System.out.println("\t2-" + irAPaseoDelPrado.toString());
        System.out.println("\t3-" + veCarcel.toString());
        System.out.println("\t4-" + impuestoPropiedades.toString());
        System.out.println("\t5-" + paga.toString());
        System.out.println("\t6-" + pagaTodos.toString());
        
        //Probando usada
        System.out.println("*Probando usada....");
        libreCarcel.usada();

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());

        //Probando salir del mazo
        System.out.println("*Probando salir del mazo....");
        libreCarcel.salirDelMazo();

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());
        
        //Probando aplicar a jugador
        Jugador j1 = new Jugador ("Jesus");
        Jugador j2 = new Jugador ("Nuria");
        Jugador j3 = new Jugador ("Jose Manu");
        Jugador j4 = new Jugador ("Julio");
        
        ArrayList<Jugador> todos = new ArrayList<>();
        todos.add(j1);todos.add(j2);todos.add(j3);todos.add(j4);

        //Probando aplicar a jugador
        System.out.println("*Probando aplicar a jugador....");
        libreCarcel.aplicarAJugador(0,todos);
//p3        irAPaseoDelPrado.aplicarAJugador(0,todos);
///        veCarcel.aplicarAJugador(0,todos);
   //     impuestoPropiedades.aplicarAJugador(0,todos);
     //   paga.aplicarAJugador(0,todos);
       // pagaTodos.aplicarAJugador(0,todos);
        
        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());
        

        //Probando informe

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());
        
        //Probando jugador correcto
        System.out.println("*Probando jugador correcto....");
        System.out.println("\t-4 --> " + libreCarcel.jugadorCorrecto(4, todos));
        System.out.println("\t-0 --> " +libreCarcel.jugadorCorrecto(-5, todos));
        System.out.println("\t-(-5) --> " +libreCarcel.jugadorCorrecto(0, todos));
        System.out.println("\t-3 --> "+ libreCarcel.jugadorCorrecto(3, todos));
    }
}
