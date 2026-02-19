package modelo;

public class PlazaGaraje {

    //Enumero el estado
    public enum Estado{
        LIBRE, VENDIDA;
    }

    //defino las variables
    double precio = 0;
    double metrosCuadrados = 0;
    String dniComprador ;
    double UMBRAL_GRANDE = 12;  //revisar
    Estado estado;

    //contructor
    public PlazaGaraje(double precio, double metrosCuadrados){
        this.estado = Estado.LIBRE;
        this.precio= precio;
        this.metrosCuadrados = metrosCuadrados;
        dniComprador = null;

    }

    //Getter y setter

    public Estado getEstado(){
        return estado;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    

}


