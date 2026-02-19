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

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public double getMetrosCuadrados(){
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados){
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getDniComprador() {
        return dniComprador;
    }

    public void setDniComprador(String dniComprador){
        this.dniComprador = dniComprador;
    }
}


