package civitas;

//import civitas.TipoCasilla;
//import civitas.TituloPropiedad;
//import civitas.Sorpresa;
//import civitas.MazoSorpresas;

import java.util.ArrayList;

public class Casilla {

    //-------------------------------------------------------------
    //Atributos de referencia
    TipoCasilla tipo;
    TituloPropiedad tituloPropiedad;
    Sorpresa sorpresa;
    MazoSorpresas mazo;
    
    //Atributos de instancia
    private String nombre;
    private float importe;
    
    //Atributos de clase
    private static int carcel = -1;

    //-------------------------------------------------------------
    //Métodos
    
    Casilla (String nombre){
        init();
        this.nombre = nombre;
        tipo = TipoCasilla.DESCANSO;
    }
    
    Casilla (TituloPropiedad titulo){
        init();
        tituloPropiedad = titulo;
        tipo = TipoCasilla.CALLE;
        nombre = titulo.getNombre();
    }
    
    Casilla (String nombre , float cantidad){
        init();
        tipo = TipoCasilla.IMPUESTO;
        importe = cantidad;
        this.nombre = nombre;
    }
    
    Casilla (String nombre, int numCasillaCarcel){
        init();
        tipo = TipoCasilla.JUEZ;
        this.nombre = nombre;
        carcel = numCasillaCarcel;
    }
    
    Casilla (MazoSorpresas mazo, String nombre ) {
        init();
        tipo = TipoCasilla.SORPRESA;
        this.mazo = mazo;
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    TituloPropiedad getTituloPropiedad (){
        return tituloPropiedad;
    }
    
    private void informe (int actual, ArrayList<Jugador> todos){
        
        String evento = "El jugador " + todos.get(actual).getNombre()
                + " ha caido en la casilla " + this.getNombre();
        
        //Informar al diario de que he caido en la casilla
        Diario.getInstance().OcurreEvento(evento);
    }
    
    private void init(){
        //Atributos de referencia
        tipo = null;
        tituloPropiedad = null;
        sorpresa= null;
        mazo= null;

        //Atributos de instancia
        nombre = "";
        importe = 0;

        //Atributos de clase
        /*
        Quito la cárcel porque es static y podría modificar el valor
        que quiero que reciba si no declaro la casilla que recibe la cárcel
        como la última
        */
    }
    
    public Boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        return (todos.size()>actual) && (actual>=0);
    }
    
    void recibeJugador (int actual , ArrayList<Jugador> todos){
        switch (tipo){
            case CALLE:
                recibeJugador_calle(actual,todos);
                break;
                
            case IMPUESTO:
                recibeJugador_impuesto(actual,todos);
                break;
                
            case JUEZ:
                recibeJugador_juez(actual,todos);
                break;
                
            case SORPRESA:
                recibeJugador_sorpresa(actual,todos);
                break;
                
            default:
                informe(actual,todos);
                break;
        }
    }
    
    private void recibeJugador_calle (int actual, ArrayList<Jugador> todos ){
        if (jugadorCorrecto(actual,todos)){
            
            informe(actual,todos);
            
            if (!tituloPropiedad.tienePropietario() )
            {
                todos.get(actual).puedeComprarCasilla();
            }
            
            else  
            {
                    tituloPropiedad.tramitarAlquiler(todos.get(actual)); 
            }
            
        }
    }
    
    private void recibeJugador_impuesto (int actual, ArrayList<Jugador> todos ){
        
        if (jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            
            //Jugador paga impuesto
            todos.get(actual).pagaImpuesto(importe);
        }
    }
    
    private void recibeJugador_juez (int actual, ArrayList<Jugador> todos ){
       
        if (jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            
            //Encarcela al jugador
            todos.get(actual).encarcelar(carcel);
        }
    }
    
    private void recibeJugador_sorpresa (int actual, ArrayList<Jugador> todos ){
        if (jugadorCorrecto(actual,todos)){
            //1
            Sorpresa sorpresa = mazo.siguiente();
            
            //2
            informe(actual,todos);
            
            //3
            sorpresa.aplicarAJugador(actual, todos);
        }
    }
    
    @Override
    public String toString () {
        String representacion;
        
        representacion = "Casilla: \n"
                        +"\t- Nombre = " + nombre + "\n";
        
        if (tituloPropiedad != null)
            representacion += "\t- Titulo de propiedad = " + tituloPropiedad.toString() + "\n";
        
        if (sorpresa != null)
            representacion += "\t- Sorpresa = " + sorpresa.toString() + "\n";
        
        return representacion;
    }
    
    //-------------------------------------------------------------
    
    public static void main (String args[]){
        
        
        //Declaracion de variables
        TituloPropiedad Titulo1 = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        MazoSorpresas mazo1 = new MazoSorpresas ();
        
        Casilla descanso = new Casilla("Descanso");
        Casilla calle = new Casilla(Titulo1);
        Casilla impuesto = new Casilla("Impuesto por ser tan guapo", 1500);
        Casilla juez = new Casilla("Juzgados", 3);
        Casilla sorpresa = new Casilla(mazo1, "Quedas libre de la carcel");
        
        
        //Probando constructores
        System.out.println("*Probando constructores...");

            //Probando métodos get
            System.out.println("\t\n-Con métodos get...\n");
            System.out.println("\t*Descanso: ");
                System.out.println("\t\tNombre: " + descanso.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + descanso.getTituloPropiedad());
            System.out.println("\t*Calle: ");
                System.out.println("\t\tNombre: " + calle.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + calle.getTituloPropiedad());
            System.out.println("\t*Impuesto: ");
                System.out.println("\t\tNombre: " + impuesto.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + impuesto.getTituloPropiedad());
            System.out.println("\t*Juez: ");
                System.out.println("\t\tNombre: " + juez.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + juez.getTituloPropiedad());
            System.out.println("\t*Sorpresa: ");
                System.out.println("\t\tNombre: " + sorpresa.getNombre());
                System.out.println("\t\tTitulo de Propiedad: " + sorpresa.getTituloPropiedad());
                
            //Probando to string
            System.out.println("\t\n-Y con to String...\n");
            System.out.println("\t\t*Descanso..." + descanso.toString());
            System.out.println("\t\t*Calle..." + calle.toString());
            System.out.println("\t\t*Impuesto..." + impuesto.toString());
            System.out.println("\t\t*Juez..." + juez.toString());
            System.out.println("\t\t*Sorpresa..." + sorpresa.toString());
            
        //Resto de métodos
            //Declaracion de variables
        Jugador j1 = new Jugador ("Jesus");
        Jugador j2 = new Jugador ("Nuria");
        Jugador j3 = new Jugador ("Jose Manu");
        Jugador j4 = new Jugador ("Julio");
        
        ArrayList<Jugador> todos = new ArrayList<>();
        todos.add(j1);todos.add(j2);todos.add(j3);todos.add(j4);
        
            //Métodos recibe+jugadorcorrecto+informe
        impuesto.recibeJugador_impuesto(0, todos);
        juez.recibeJugador_juez(0, todos);
        
        System.out.println("Leyendo diario...");
        while(Diario.getInstance().EventosPendientes())
                System.out.println(Diario.getInstance().LeerEvento());
        
        
        
        
    }
}
