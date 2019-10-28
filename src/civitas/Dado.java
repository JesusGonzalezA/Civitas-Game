package civitas;

import java.util.Random;




public class Dado {
    
    //-----------------------------------------------------------------
    //Atributos de clase
    static final private Dado INSTANCE = new Dado();
    static private int SalidaCarcel = 5;
    
    //Atributos de instancia
    private Random random;
    private int ultimoResultado;
    private Boolean debug;
    //-----------------------------------------------------------------
    //Constructor
    
    private Dado (){
        //Actualizo todos los atributos de instancia
        debug = false;
        ultimoResultado = 0;
        random = new Random();
    }
    
    //-----------------------------------------------------------------
    //Métodos de clase
    
    public static Dado getInstance(){
        return INSTANCE;
    }
    
    //-----------------------------------------------------------------
    //Métodos de instancia
    
    int tirar () {
        
        ultimoResultado = 1;
       
        if (!debug){
            ultimoResultado = 1 + (random.nextInt(6));          //modulo 6 --> numero entre 0 y 5, + 1 --> 1 a 6
        }
        
        return ultimoResultado;
    }
    
    Boolean salgoDeLaCarcel() {
        
        //Tiro + salgo si ultimoResultado es mayor o igual que SalidaCarcel
        
        return (tirar() >= SalidaCarcel);
    }
    
    int quienEmpieza (int n) {
        //Devuelve numero ∈ [0,n-1]
        return (random.nextInt(n));
    }
    
    public void setDebug (Boolean d){
        debug = d;
        (Diario.getInstance()).OcurreEvento("Se modifica modo debug a " + d);
    }
    
    int getUltimoResultado (){
        return ultimoResultado;
    }
}
