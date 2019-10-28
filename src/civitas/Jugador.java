package civitas;

//import civitas.TituloPropiedad;
//import civitas.Sorpresa;

import java.util.ArrayList;

public class Jugador implements Comparable<Jugador>{
    
    //-------------------------------------------------------------
    //Atributos de referencia
    private Sorpresa salvoconducto;
    private ArrayList<TituloPropiedad> propiedades;
    
    //Atributos de instancia
    protected Boolean encarcelado;
    private String nombre;
    private int numCasillaActual;
    private Boolean puedeComprar;
    private float saldo;
    
    
    //Atributos de clase
    protected static final int CasasMax = 4;
    protected static final int CasasPorHotel = 4;
    protected static final int HotelesMax = 4;
    protected static final float PasoPorSalida = 1000.0f;
    protected static final float PrecioLibertad = 200.0f;
    private static final float SaldoInicial = 7500.0f;
    
    //-------------------------------------------------------------
    //Métodos
    
    Boolean cancelarHipoteca(int ip){       //p3
        
        Boolean result = false;
        
        if (!encarcelado && existeLaPropiedad(ip)){
            
            //Obtener precio cancelación para ver si puede gastarla
            TituloPropiedad propiedad = propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            Boolean puedoGastar = puedoGastar(cantidad);
            
            if (puedoGastar){
                //Se encarga de poner hipotecado a falso y hacer que pague
                result = propiedad.cancelarHipoteca(this);
            }
            
            if (result){
                String evento = "El jugador " + nombre 
                                + " cancela la hipoteca de la propiedad " + ip;
                Diario.getInstance().OcurreEvento(evento);
            }
        }
        
        return result;
    }
    
    int cantidadCasasHoteles(){
        int contador = 0;
        int numPropiedades = propiedades.size();
        
        for (int i=0; i<numPropiedades; ++i){
            contador += propiedades.get(i).cantidadCasasHoteles();
        }
        
        return contador;
    }
    
    @Override
    //Devuelvo 0 si son iguales los saldos, positivo si this tiene más y
    //negativo si otro tiene más
    public int compareTo(Jugador otro){
        return (int)(this.saldo-otro.saldo);
    }
    
    Boolean comprar(TituloPropiedad titulo){   //p3
        Boolean result = false;
        
        if (!encarcelado && puedeComprar){
            float precio = titulo.getPrecioCompra();
            if (puedoGastar(precio)){
                //Hace pagar y actualiza propietario
                result = titulo.comprar(this);
             
                //Añado a propiedades y aviso a diario
                if (result){
                    propiedades.add(titulo);
                    
                    String evento = "El jugador " + nombre 
                                + " compra la propiedad " + titulo.getNombre();//titulo.toString();
                    Diario.getInstance().OcurreEvento(evento);
                }
            }
            puedeComprar = false;
        }
        
        return result;
    }
    
    Boolean construirCasa (int ip){     //p3
        
        Boolean result = false;
        Boolean puedoEdificarCasa = false;
        
        if (!encarcelado && existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            puedoEdificarCasa = puedoEdificarCasa(propiedad);
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoEdificarCasa)
                result = propiedad.construirCasa(this);
        }
        
        if (result)
        {
            String evento = "El jugador " + nombre + " construye casa en la "
                    +  "propiedad " + ip;
            Diario.getInstance().OcurreEvento(evento);
        }
        
        return result;
    }
    
    Boolean construirHotel(int ip){     //p3
        
        Boolean result = false;
        
        if (!encarcelado && existeLaPropiedad(ip)){
            
            //Obtengo el precio de la propiedad y veo si puedo edificar
            TituloPropiedad propiedad = propiedades.get(ip);
            Boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
            float precio = propiedad.getPrecioEdificar();
            
            if (puedoEdificarHotel){
                
                //Paga y incrementa hoteles
                result = propiedad.construirHotel(this);
                
                //Derruir casas
                int casasPorHotel = getCasasPorHotel();
                propiedad.derruirCasas(casasPorHotel,this);
                
                //Informar a diario
                String evento = "El jugador " + nombre + " construye hotel "
                       + "en la propiedad " + ip + ", " + propiedad.getNombre();
                Diario.getInstance().OcurreEvento(evento);
            }
        }
        
        return result;
    }
    
    protected Boolean debeSerEncarcelado(){
        Boolean debe = false;
        
        if (!encarcelado){
            if(!tieneSalvoconducto())
                debe = true;
            else{
                perderSalvoconducto();
                Diario.getInstance().OcurreEvento("El jugador " + nombre
                            + " se libra de la cárcel");   
            }
                
        }
        
        return debe;
    }
    
    Boolean enBancarrota(){
        return saldo<0;
    }
    
    Boolean encarcelar (int numCasillaCarcel){
        
        Boolean aCarcel = false;
        
        if (debeSerEncarcelado()){
            numCasillaActual = numCasillaCarcel;
            encarcelado = true;
            Diario.getInstance().OcurreEvento("El jugador " + nombre
                            + " va a la cárcel");
            aCarcel = true;
        }
        
        return aCarcel;     //copia para seguridad encarcelado
    }
    
    private Boolean existeLaPropiedad(int ip){
        return (propiedades.size()>ip)&&(ip>=0);
    }
    
    private int getCasasMax(){
        return CasasMax;
    }
    
    int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    private int getHotelesMax(){
        return HotelesMax;
    }
    
    protected String getNombre(){
        return nombre;
    }
    
    int getNumCasillaActual(){
        return numCasillaActual;
    }
    
    private float getPrecioLibertad(){
        return PrecioLibertad;
    }
    
    private float getPremioPasoPorSalida(){
        return PasoPorSalida;
    }
    
    protected ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    Boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    protected float getSaldo(){
        return saldo;
    }
    
    Boolean hipotecar (int ip){     //p3
        Boolean result = false;
        
        if (!encarcelado && existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            result = propiedad.hipotecar(this);
        }
        
        if (result){
            String evento = "El jugador " + nombre + " hipoteca la propiedad "
                           + ip;
            Diario.getInstance().OcurreEvento(evento);
        }
        
        return result;
    }
    
    public Boolean isEncarcelado(){
        return encarcelado;
    }
 
    
    Jugador (String nombre){
        
        //Inicializo atributos de referencia
        salvoconducto = null;
        propiedades = new ArrayList<>();
        
        //Inicializo atributos de instancia
        encarcelado = false;
        this.nombre = nombre;
        numCasillaActual = 0;
        puedeComprar = false;
        saldo = SaldoInicial;
        
    }
    
    protected Jugador (Jugador otro){
        //Desde una instancia de una clase puedo acceder a todos los elementos
        //de otra instancia de la misma clase
        
        //Inicializo atributos de referencia
        salvoconducto = otro.salvoconducto;
        propiedades = otro.propiedades;
        
        //Inicializo atributos de instancia
        encarcelado = otro.encarcelado;
        nombre = otro.nombre;
        numCasillaActual = otro.numCasillaActual;
        puedeComprar = otro.puedeComprar;
        saldo = otro.saldo;
    }
    
    Boolean modificarSaldo(float cantidad){
        
        saldo += cantidad;
        String aumento_decremento;
        
        if (cantidad<0){
            aumento_decremento = "decrementa";
            cantidad *=-1;
        }
        else
            aumento_decremento = "aumenta";
        
        Diario.getInstance().OcurreEvento("El saldo del jugador " + nombre + " "
                                 + aumento_decremento + " " + cantidad);
       
        return true;
    }
    
    Boolean moverACasilla (int numCasilla){
        Boolean movida = false;
        if (!isEncarcelado())
        {
            numCasillaActual = numCasilla;
            puedeComprar = false;
            Diario.getInstance().OcurreEvento("El jugador " + nombre 
                                  + " avanza a la casilla " + numCasilla);
            movida = true;
        }
        
        return movida;
    }
    
    Boolean obtenerSalvoconducto (Sorpresa sorpresa){
        
        Boolean obtenido = false;
        if (!isEncarcelado())
        {
            salvoconducto = sorpresa;
            obtenido = true;
        }
        
        return obtenido;
    }
    
    Boolean paga (float cantidad){
        return modificarSaldo(-cantidad);
    }
    
    Boolean pagaAlquiler (float cantidad){
        Boolean pagado = false;
        
        if (!isEncarcelado()){
            pagado = paga(cantidad);
        }
            
        return pagado;
    }
    
    Boolean pagaImpuesto(float cantidad){
        Boolean pagado = false;
        
        if (!isEncarcelado()){
            pagado = paga(cantidad);
        }
            
        return pagado;
    }
    
    Boolean pasaPorSalida (){
        
        Diario.getInstance().OcurreEvento("El jugador " + nombre 
                        + " pasa por la casilla de salida." );
        modificarSaldo(PasoPorSalida);
        
        return true;
    }
    
    private void perderSalvoconducto(){
        salvoconducto.usada();
        salvoconducto = null;
    }
    
    Boolean puedeComprarCasilla(){
        puedeComprar = !isEncarcelado();    //Si no está encarcelado puede comprar
        return puedeComprar;
    }
    
    private Boolean puedeSalirCarcelPagando(){
        return saldo>=PrecioLibertad;
    }
    
    private Boolean puedoEdificarCasa(TituloPropiedad propiedad){
        
        Boolean puedo = propiedades.contains(propiedad)
                        && puedoGastar(propiedad.getPrecioEdificar())
                        && (propiedad.getNumCasas()<CasasMax);
        return puedo;
    }
    
    private Boolean puedoEdificarHotel(TituloPropiedad propiedad){
        Boolean puedo = propiedades.contains(propiedad)
                      &&  puedoGastar(propiedad.getPrecioEdificar())
                      && ((propiedad.getNumCasas())>=CasasPorHotel)
                      && (propiedad.getNumHoteles()<HotelesMax);
        
        return puedo;
    }
    
    private Boolean puedoGastar(float precio){
        Boolean puedo = false;
        
        if (!isEncarcelado()){
            puedo = (saldo >= precio);
        }
        
        return puedo;
    }
    
    Boolean recibe(float cantidad){
        Boolean recibido = false;
        
        if (!isEncarcelado()){
            recibido = modificarSaldo(cantidad);
        }
        
        return recibido;
    }
    
    Boolean salirCarcelPagando(){
        
        Boolean sale = false;
        
        if (isEncarcelado() && puedeSalirCarcelPagando()){
            paga(PrecioLibertad);
            encarcelado = false;
            Diario.getInstance().OcurreEvento("El jugador " + nombre
                                + " sale de la cárcel pagando.");
            sale = true;
        }
            
        
        return sale;
    }
    
    Boolean salirCarcelTirando(){
        Boolean sale = false;
        
        if(Dado.getInstance().salgoDeLaCarcel()){
            encarcelado = false;
            Diario.getInstance().OcurreEvento("El jugador " + nombre
                                + " sale de la cárcel tirando.");
            sale = true;
        }
        
        return sale;
    }
    
    Boolean tieneAlgoQueGestionar(){
        return !propiedades.isEmpty();
    }
    
    Boolean tieneSalvoconducto(){
        return (salvoconducto!=null);
    }
    
    private String infoPropiedades (){
        String rep_propiedades = "";
        
        if (!propiedades.isEmpty()){
            int tam = propiedades.size()-1;

            for (int i=0; i<tam; ++i)
                rep_propiedades += ( propiedades.get(i).getNombre() + ", ");
            rep_propiedades += propiedades.get(tam).getNombre() + ".";
        }
        
        else{
            rep_propiedades = "no tiene propiedades.";
        }
        
        return rep_propiedades;
    }
    
    @Override
    public String toString(){
        String representacion;
        String rep_propiedades = infoPropiedades();
        
        representacion = "Jugador: \n"
                        +"\t- Nombre          = " + nombre + "\n"
                        +"\t- Saldo           = " + saldo + "\n"
                        +"\t- Casilla actual  = " + numCasillaActual + "\n"
                        +"\t- Puede comprar   = " + puedeComprar + "\n"
                        +"\t- Encarcelado     = " + encarcelado + "\n"
                        +"\t- Salvoconducto   = " + tieneSalvoconducto() + "\n"
                        +"\t- Propiedades     = " + rep_propiedades + "\n"
                        +"\t- Máximo casas    = " + CasasMax + "\n"
                        +"\t- Máximo hoteles  = " + HotelesMax + "\n"
                        +"\t- Casas por Hotel = " + CasasPorHotel + "\n"
                        +"\t- Paso por salida = " + PasoPorSalida + "\n"
                        +"\t- Precio libertad = " + PrecioLibertad + "\n"
                        +"\t- Saldo inicial   = " + SaldoInicial + "\n";
        
        return representacion;
    }
    
    
    Boolean vender(int ip){
       throw new UnsupportedOperationException("No implementado");
    }
    
    //-------------------------------------------------------------
    
    public static void main (String args[]){
        
        //Declaracion de variables
        Jugador j1 = new Jugador("Jesus");
        Jugador j2 = new Jugador(j1);
       
        TituloPropiedad Titulo1 = new TituloPropiedad("Paseo del Prado",
                50.0f,100.0f,2000.0f,4000.0f,1000.0f);
        
        //Pruebo constructor
        System.out.println("*Probando constructor...");
        
        System.out.println(j1.toString());
        System.out.println(j2.toString());
        
        
        //Get
        System.out.println("*Probando métodos get...");
        System.out.println(j1.getCasasMax());
        System.out.println(j1.getCasasPorHotel());
        System.out.println(j1.getHotelesMax());
        System.out.println(j1.getNombre());
        System.out.println(j1.getNumCasillaActual());
        System.out.println(j1.getPrecioLibertad());
        System.out.println(j1.getPremioPasoPorSalida());
        System.out.println(j1.getPropiedades());
        System.out.println(j1.getPuedeComprar());
        System.out.println(j1.getSaldo());
        
        //______________________________________________________________________
                       //Cambios de estado para probar métodos
        //______________________________________________________________________
        
        //Añado propiedad para probar:
        //-cantidadCasasHoteles()
        //-existeLaPropiedad()
        //-puedoEdificarCasa()
        //-puedoEdificarHotel()
        //-tieneAlgoQueGestionar()
            
            //Añado el titulo y actualizo el propietario
        j1.propiedades.add(Titulo1);
        Titulo1.actualizaPropietarioPorConversion(j1);
            //Actualizo propiedades
        Titulo1.construirCasa(j1);
        Titulo1.construirCasa(j1);
        Titulo1.construirCasa(j1);
        Titulo1.construirCasa(j1);
        
        //Cambio saldo de j2 para probar:
        //-compareTo()
        j2.saldo = -j1.saldo;
        
        //Pruebo a encarcelarlo y darle salvoconducto
        //-DebeSerEncarcelado()
        //j1.encarcelado = true;
        MazoSorpresas mazo1 = new MazoSorpresas();
        //Boolean obtenido = j1.obtenerSalvoconducto(new Sorpresa(TipoSorpresa.SALIRCARCEL, mazo1));
        

        //Cambio saldo de j1 a negativo y 0 para probar:
        //-enBancarrota()
        j1.saldo = 0;
        
        //Pruebo a encarcelarlo
        //-encarcelar()
        //-isEncarcelado() si comento método encarcelar()
        //-puedoGastar()
        //-recibir()
        //-salirCarcelPagando()
        j1.encarcelado = false;
      
        ////////////////////////////////////////////////////////////////////////
        
        //Otros métodos
        System.out.println("\n*Probando el resto de métodos....\n");
        System.out.println("*Cantidad casas y hoteles: " + j1.cantidadCasasHoteles());
        System.out.println("*j1.compareTo(j2) : " + j1.compareTo(j2));
        System.out.println("*Debe ser encarcelado: " + j1.debeSerEncarcelado());
        System.out.println("*En bancarrota: " + j1.enBancarrota());
        //System.out.println("*Encarcelar(99): " + j1.encarcelar(99));    
        System.out.println("*Existe la propiedad(0): " + j1.existeLaPropiedad(0));
        System.out.println("*Esta encarcelado: " + j1.isEncarcelado());
        System.out.println("*Modificar saldo + " + SaldoInicial + " : " + j1.modificarSaldo(SaldoInicial));
        System.out.println("*Mover a casilla 6: " + j1.moverACasilla(6));
        System.out.println("*Obtener salvoconducto: " + j1.obtenerSalvoconducto(new Sorpresa(TipoSorpresa.SALIRCARCEL, mazo1)));
        System.out.println("    numCasillaActual-->"+j1.getNumCasillaActual());
        System.out.println("*Puede comprar casilla: " + j1.puedeComprarCasilla());
        System.out.println("*Puedo salir de la carcel pagando: " + j1.puedeSalirCarcelPagando());
        System.out.println("*Puedo edificar casa: " + j1.puedoEdificarCasa(Titulo1));
        System.out.println("*Puedo edificar hotel: " + j1.puedoEdificarHotel(Titulo1));
        System.out.println("*Puedo gastar " + j1.saldo + " : " + j1.puedoGastar(j1.saldo));
        System.out.println("*Recibe " + SaldoInicial + " : " + j1.recibe(SaldoInicial));
        System.out.println("*Salir carcel pagando: " + j1.salirCarcelPagando());
        System.out.println("*Salir carcel tirando: " + j1.salirCarcelTirando());
        System.out.println("*Tiene algo que gestionar: " + j1.tieneAlgoQueGestionar());
        System.out.println("*Paga alquiler: " + j1.pagaAlquiler(SaldoInicial));
        System.out.println("*Paga impuesto: " + j1.pagaImpuesto(SaldoInicial));
        System.out.println("*Pasa salida: " + j1.pasaPorSalida());
        System.out.println("*Paga: " + j1.paga(SaldoInicial));
        System.out.println("*Existe la propiedad: " + j1.existeLaPropiedad(1));
//        System.out.println("*Vender: " + j1.vender(1));
        
        System.out.println("*Tiene salvoconducto: " + j1.tieneSalvoconducto());
        System.out.println(j1);
        
        //Leer diario
        System.out.println("Leyendo el diario...");
        while (Diario.getInstance().EventosPendientes())
            System.out.println("\t-" + Diario.getInstance().LeerEvento());
        
    }
    
}
