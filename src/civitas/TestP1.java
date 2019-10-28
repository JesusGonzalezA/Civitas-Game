package civitas;

public class TestP1 {
    public static void main (String args[]){
        
        //-------------------------------------------------------------------
        //DECLARACION DE VARIABLES
        
        //CASILLA
        final String NOMBRECASILLA = "Etsiit";
        final Casilla CASILLA1 = new Casilla ("Casa");
        final Casilla CASILLA2 = new Casilla ("Trabajo");
        final Casilla CASILLA3 = new Casilla ("Los Cármenes");
        
        //TABLERO
        final int SIZE = 5; //AÑADIR TANTAS CASILLAS COMO SIZE
        final int INDICECARCEL = 1;
        final int INDICESALIDA = 0;
        final int INDICECARCEL_DEFECTO = 1;
        final int CASILLA_ACTUAL = 4;
        final int CASILLA_FINAL = 0;
        final int TIRADA = 5;
        
        //DIARIO
        final int LONGCADENA= 5;
        String cadena[] = new String[LONGCADENA];
        cadena[0] = "Hola";
        cadena[1] = "Soy";
        cadena[2] = "Un";
        cadena[3] = "Nuevo";
        cadena[4] = "Evento";
        
        //DADO
        final int CARAS_DADO = 6;
        final int NUM_JUGADORES = 4;
        final int NUM_PRUEBAS_QUIEN_EMPIEZA = 100;
        int JUGADOR[] = new int [NUM_JUGADORES];
        int APARICIONES[] = new int [NUM_JUGADORES];
        for (int i=0; i<NUM_JUGADORES; ++i)
        {
            JUGADOR[i] = i;
            APARICIONES[i] = 0;
        }
        final int NUM_PRUEBAS = 100;
        int pruebas[] = new int [CARAS_DADO];
        for (int i=0; i<CARAS_DADO; ++i){
            pruebas[i] = 0;
        }
        
        int total_tiradas = 0;
        int veces_salgo = 0;
                
        //Mazo Sorpresas
        MazoSorpresas Mazo1 = new MazoSorpresas(true);
        MazoSorpresas Mazo2 = new MazoSorpresas();
        
        
        //-------------------------------------------------------------------
        //TEST
        //------------------CASILLA------------------------------------------
         Casilla casilla = new Casilla (NOMBRECASILLA);
         System.out.println(casilla.getNombre());
         
        //------------------TABLERO------------------------------------------
        //Estudio comportamiento del constructor
        System.out.println ("-----------------------------------------");        
        System.out.print ("Creando tablero");
        System.out.println ("  con indice de la carcel = " + INDICECARCEL);
        Tablero tab1 = new Tablero (INDICECARCEL);
        
            //Muestro que el constructor ha inicializado bien el objeto
        System.out.println( "\t*La carcel esta en " + tab1.getCarcel() );
        System.out.println ("\t*Por Salida --> " + tab1.getPorSalida());
        
                
//                no lo compruebo porque como no he formado el tablero no es correcto
//                y hago getNombre sobre un null que es lo que me devuelve
//                al hacer getCasilla con indice 0
                
                //System.out.println ("\t*Compruebo si añado bien casilla 0 Salida ...");
                //System.out.println(tab1.getCasilla(INDICESALIDA).getNombre());
        
        //Compruebo el resto de metodos
                //Hago que el tablero pueda ser correcto
        System.out.println("Añadiendo casillas...");
        System.out.println("Nombre casillas en orden:");
        tab1.añadeJuez();
        tab1.añadeCasilla(CASILLA1);
        tab1.añadeCasilla(CASILLA2);
        tab1.añadeCasilla(CASILLA3);
        
        for (int i=0; i<=SIZE; ++i)
            System.out.println ("\t" + i + "-->" + tab1.getCasilla(i).getNombre());
//                
//                -Metodo añadeCasilla comprobado accediendo a los elementos de casilla
//                desde la clase. Si uso metodo get necesito montar el tablero.
//                -Comprobado el funcionamiento de metodos correcto(*) en el caso
//                que tenga que dar valor incorrecto, falta probarlo montando el 
//                tablero
//                -GetPorSalida comprobado iniciando el valor de PorSalida a >0
//                
        

        //Las tendre que valorar posteriormente con un tablero correcto
        //Tengo cuidado si he usado antes getPorSalida 
        System.out.println ( "\n\n"+
                "Si mi posicion actual es " + CASILLA_ACTUAL + " y mi tirada es"
                 + " " + TIRADA + " me ubicare en la casilla " + 
                 tab1.nuevaPosicion(CASILLA_ACTUAL, TIRADA));
        System.out.println ("He realizado una tirada de " + 
                tab1.calcularTirada(CASILLA_ACTUAL, CASILLA_FINAL) + " para "
                + " avanzar de " + CASILLA_ACTUAL + " a " + CASILLA_FINAL);
        
        //------------------DIARIO ------------------------------------------
        Dado dado = Dado.getInstance();
        dado.setDebug(Boolean.TRUE);
        
        Diario DiarioDeJuego = Diario.getInstance();
        System.out.println ("-----------------------------------------");
        System.out.println ("\nAnalizando el comportamiento de Diario...");
        for (int i=0; i<LONGCADENA; ++i)
            DiarioDeJuego.OcurreEvento(cadena[i]);
        
        if (DiarioDeJuego.EventosPendientes()){
            System.out.println ("Hay eventos pendientes: ");
            
            while (DiarioDeJuego.EventosPendientes())
                System.out.println("\t" + DiarioDeJuego.LeerEvento());
        }
        else
            System.out.println("No hay eventos pendientes");
        
        
        
        
        //------------------DADO   ------------------------------------------
        System.out.println ("-----------------------------------------");
        System.out.println("Analizando el comportamiento del dado..." );
        System.out.println ("El ultimo resultado ha sido: " +
                                    dado.getUltimoResultado());
        
        
        //Prueba del metodo quien empieza
        for (int i=0; i<NUM_PRUEBAS_QUIEN_EMPIEZA; ++i){
            APARICIONES[dado.quienEmpieza(NUM_JUGADORES)]++;
        }
        
        System.out.println("Mostrando los resultados de ejecutar "
                    + NUM_PRUEBAS_QUIEN_EMPIEZA + " quien empieza");
        
        for (int i=0; i<NUM_JUGADORES; ++i){
            System.out.println("\t*Jugador "+i + " empezó " + APARICIONES[i] 
                    + " veces");
        }
        
        //Prueba del metodo tirar y debug
        dado.setDebug(Boolean.FALSE);
        System.out.println ("Probando el metodo tirar " + NUM_PRUEBAS + " veces");
        
        for (int i=0; i<NUM_PRUEBAS; ++i){
            int tirada = dado.tirar();
            pruebas[tirada-1]++;
        }
        
        total_tiradas = 0;      //Me aseguro que el contador se reinicie
        
        for (int i=0; i<CARAS_DADO; ++i){
            System.out.println("\t"+ (i+1) + " --> " + pruebas[i] + " veces");
            total_tiradas += pruebas[i];
        }
        
        System.out.println ("             ----------");
        
        
        System.out.println ("             " + total_tiradas + " veces");
        
        //Pruebo el metodo salgo de la carcel
        System.out.println("Probando el metodo salgo de la carcel:");
        
        veces_salgo = 0; 
        for (int i=0; i<total_tiradas; ++i){
            if (dado.salgoDeLaCarcel())
                veces_salgo++;
        }
        System.out.println ("\tHe salido de la carcel " + veces_salgo + " veces"
                + " de " + total_tiradas);
        
        //------------------MAZOSORPRESAS-------------------------------------------------
        System.out.println ("-----------------------------------------");
        System.out.println("Probando el mazo sorpresas......");
        
        
            //Probando alMazo
        Sorpresa S = new Sorpresa (TipoSorpresa.IRCASILLA, 3,"Hola");
        Sorpresa S2 = new Sorpresa (TipoSorpresa.IRCASILLA, 3,"adios");
        
                //Mazo en modo debug
        System.out.println("Mostrando cartas mazo modo debug, orden hola adios hola ...");
        Mazo1.alMazo(S);
        Mazo1.alMazo(S2);
        System.out.println("\t" + Mazo1.siguiente().toString());
        System.out.println("\t" + Mazo1.siguiente().toString());
        System.out.println("\t" + Mazo1.siguiente().toString());
        
                //Mazo sin modo debug
        System.out.println("Mostrando cartas mazo modo debug, orden indefinido ...");

        Mazo2.alMazo(S);    
        Mazo2.alMazo(S2);
        System.out.println("\t" + Mazo2.siguiente().toString());
        System.out.println("\t" + Mazo2.siguiente().toString());
        System.out.println("\t" + Mazo2.siguiente().toString());
        
        
        System.out.println("Voy a inhabilitar la carta sorpresa "  + S.toString()
               + " y a habilitarla para ver el diario: ");
        Mazo1.inhabilitarCartaEspecial(S);
        Mazo1.habilitarCartaEspecial(S);
        while (Diario.getInstance().EventosPendientes())
            System.out.println("\t" + Diario.getInstance().LeerEvento());
        


        //-------------------------------------------------------------------
        //-------------------------------------------------------------------
        //-------------------------------------------------------------------
        //-------------------------------------------------------------------

    }
}
