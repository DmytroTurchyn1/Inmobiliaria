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


    //Hago metodos/funciones

    public boolean esGrande(){
        return metrosCuadrados > UMBRAL_GRANDE;
    }

    public boolean cumpleSuperficie(double supMax, double supMin){
        if (metrosCuadrados >= supMin && metrosCuadrados <= supMax){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean cumplePrecio(double precioMax, double precioMin ){
        return precio >= precioMin && precio <= precioMax;
    }

    public boolean cumpleTamano(int filtroTamano){
        if (filtroTamano == 0){
            return true;
        }
        if (filtroTamano == 1){
            return !esGrande();
        }
        if (filtroTamano == 2){
            return esGrande();
        }
        return false;

    }

    //metodos/funcion de transacciones

    public boolean vender(String dniComprador){
        if (dniComprador == null || dniComprador.isEmpty()){
            return false;
        }
        else {
            estado = Estado.VENDIDA;
            this.dniComprador = dniComprador;
            return true;
        }
    }

    public void liberar(){
        estado = Estado.LIBRE;
        dniComprador = null;
    }


    @Override
    public String toString() {
        return switch (this.estado) {
            case LIBRE -> "L";
            case VENDIDA -> "V";
        };
    }

    public String getDetalles() {
        String tamano ;

        if(esGrande()){
            tamano = "GRANDE";
        }
        else{
            tamano = "PEQUEÑA";
        }

        return  "DETALLES :"+
                "\nESTADO -> "+estado+
                "\nTAMAÑO ->"+tamano+
                "\nPRECIO -> "+ precio+
                "\nMETROS^2 -> "+ metrosCuadrados+
                "\nDNI COMPRADOR ->"+dniComprador;
    }



}


