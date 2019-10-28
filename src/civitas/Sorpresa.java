package civitas;

//import civitas.Tablero;
//import civitas.MazoSorpresas;
//import civitas.TipoSorpresa;

import java.util.ArrayList;


public class Sorpresa {
    
    //-------------------------------------------------------------
    private static final int VALORNULO = 0;
    
    //Atributos de referencia
    private MazoSorpresas mazo;
    private TipoSorpresa tipo;
    private Tablero tablero;
    
    //Atributos de instancia
    private String texto;
    private int valor;
    
    
    //-------------------------------------------------------------
    //Métodos
    
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        
      
        switch (tipo) {
            case IRCASILLA:
                aplicarAJugador_irACasilla (actual, todos);
                break;
            case IRCARCEL:
                aplicarAJugador_irCarcel (actual,todos);
                break;
            case PAGARCOBRAR:
                aplicarAJugador_pagarCobrar (actual,todos);
                break;
            case PORCASAHOTEL:
                aplicarAJugador_porCasaHotel (actual,todos);
                break;
            case PORJUGADOR:
                aplicarAJugador_porJugador (actual,todos);
                break;
            case SALIRCARCEL:
                aplicarAJugador_salirCarcel (actual,todos);
                break;
        } 
                            
    }
    
    private void aplicarAJugador_irACasilla (int actual, ArrayList<Jugador> todos){
        
        if (jugadorCorrecto(actual, todos)){
            
           informe(actual,todos);
           
           //Ir a casilla
            
                //Calcular posicion en el tablero por si pasa por salida
           int casillaActual = (todos.get(actual).getNumCasillaActual());
           int tirada = tablero.calcularTirada(casillaActual, valor);
           casillaActual = tablero.nuevaPosicion (casillaActual, tirada);
            
                //Mover a nueva posicion del tablero
           todos.get(actual).moverACasilla(casillaActual);
            
                //La casilla recibe al jugador
           tablero.getCasilla(casillaActual).recibeJugador(actual, todos);
        }
    }
    
    private void aplicarAJugador_irCarcel (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
    
    private void aplicarAJugador_pagarCobrar (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
            //Modifico el saldo del jugador
            todos.get(actual).modificarSaldo(valor);
        }
    }
    
    private void aplicarAJugador_porCasaHotel (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
            //Modifico el saldo del jugador en funcion del numero de propiedades
            Jugador j = new Jugador (todos.get(actual));
            j.modificarSaldo(j.cantidadCasasHoteles()*valor);
        }
    }
    
    private void aplicarAJugador_porJugador (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
            //Todos los jugadores pagan al jugador actual
            String textoPagar = "Pagas "+ valor +" a "+todos.get(actual).getNombre();
            String textoCobrar = "Recibes " + valor + " de cada jugador";
            int numJugadores = todos.size();
            
            Sorpresa pagar = new Sorpresa(TipoSorpresa.PAGARCOBRAR,tablero,-valor,textoPagar);
            Sorpresa cobro = new Sorpresa(TipoSorpresa.PAGARCOBRAR,tablero,(numJugadores-1)*valor,textoCobrar);
            //Aplico pagos
           
            for (int i=0; i<numJugadores; ++i){
                if (i != actual)
                    pagar.aplicarAJugador(i,todos);
            }
            
            //Aplico cobro
            cobro.aplicarAJugador(actual, todos);
            
        }
    }
    
    private void aplicarAJugador_salirCarcel (int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            
            
            //Pregunto por el salvoconducto
            Boolean alguienTieneSV = false;
            int numJugadores = todos.size();
            
            for (int i=0; i<numJugadores; ++i)
                if (todos.get(i).tieneSalvoconducto())
                    alguienTieneSV = true;
            
            //Si nadie lo tiene lo obtiene actual y se sale del mazo
            if (!alguienTieneSV){
                todos.get(actual).obtenerSalvoconducto(this);
                this.salirDelMazo();
            }
                
        }
    }
    
    private void informe (int actual, ArrayList<Jugador> todos){
        String evento = "Se esta aplicando la sorpresa " + "'"+this.toString()
                    + "'"+ " al jugador " + todos.get(actual).getNombre();
        Diario.getInstance().OcurreEvento(evento);
    }
    
    private void init(){
        mazo = null;
        tablero = null;
        valor = VALORNULO;
    }
    
    public Boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        return (todos.size()>actual) && (actual>=0);
    }
    
    void salirDelMazo (){
        if (tipo == TipoSorpresa.SALIRCARCEL){
            mazo.inhabilitarCartaEspecial(this);
        }
    }
    
    Sorpresa (TipoSorpresa tipo, Tablero tablero){
        init();
        this.tablero = tablero;
        texto = "Ve a la Cárcel";
        this.tipo = tipo;
        valor = tablero.getCarcel();
    }
    
    Sorpresa (TipoSorpresa tipo, Tablero tablero, int valor, String texto){
        init();
        this.tipo = tipo;
        this.tablero = tablero;
        this.valor = valor;
        this.texto = texto;
    }
    
    Sorpresa (TipoSorpresa tipo, int valor, String texto){
        init();
        this.texto = texto;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    Sorpresa (TipoSorpresa tipo, MazoSorpresas mazo){
        init();
        texto = "Quedas libre de la Cárcel";
        this.mazo = mazo;
        this.tipo = tipo;
        valor = VALORNULO;
    }
    
    @Override
    public String toString () {
        return texto;
    }
    
    void usada(){
        if (tipo == TipoSorpresa.SALIRCARCEL){
            mazo.habilitarCartaEspecial(this);
        }
    }
    
    //-------------------------------------------------------------
    
    public static void main (String args[]){
        
        //Pruebo constructores
        
            //Creo Casilla
        TituloPropiedad TituloPaseoDelPrado = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        Casilla PaseoDelPrado = new Casilla (TituloPaseoDelPrado);
            
            //Creo tablero correcto
        Tablero tablero = new Tablero (1);
        tablero.añadeCasilla(PaseoDelPrado);
        tablero.añadeJuez();
        
            //Creo Mazo
        MazoSorpresas mazo1 = new MazoSorpresas();
        
        System.out.println("*Probando constructores....");
        Sorpresa libreCarcel = new Sorpresa(TipoSorpresa.SALIRCARCEL,mazo1); 
        Sorpresa irAPaseoDelPrado = new Sorpresa (TipoSorpresa.IRCASILLA, tablero,1,"Ir a Paseo del Prado");
        Sorpresa veCarcel = new Sorpresa (TipoSorpresa.IRCARCEL, tablero);
        Sorpresa impuestoPropiedades = new Sorpresa (TipoSorpresa.PORCASAHOTEL,tablero, 50,"Paga por propiedades");
        Sorpresa paga = new Sorpresa (TipoSorpresa.PAGARCOBRAR,tablero, -50,"Paga");
        Sorpresa pagaTodos = new Sorpresa (TipoSorpresa.PORJUGADOR,tablero, 50,"Paga a todos/Cobra 1");
       
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
        irAPaseoDelPrado.usada();
        veCarcel.usada();
        impuestoPropiedades.usada();
        paga.usada();
        pagaTodos.usada();

        System.out.println("\tLeyendo diario...");
        while(Diario.getInstance().EventosPendientes())
            System.out.println("\t\t" + Diario.getInstance().LeerEvento());

        //Probando salir del mazo
        System.out.println("*Probando salir del mazo....");
        libreCarcel.salirDelMazo();
        irAPaseoDelPrado.salirDelMazo();
        veCarcel.salirDelMazo();
        impuestoPropiedades.salirDelMazo();
        paga.salirDelMazo();
        pagaTodos.salirDelMazo();

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
        System.out.println("*Probando informe....");
        libreCarcel.informe(1,todos);
        irAPaseoDelPrado.informe(0,todos);
        veCarcel.informe(0,todos);
        impuestoPropiedades.informe(0,todos);
        paga.informe(0,todos);
        pagaTodos.informe(0,todos);

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
