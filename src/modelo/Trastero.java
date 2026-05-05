package modelo;


public class Trastero {

    public enum Estado{
        LIBRE, VENDIDO;
    }


    // Atributos principales
    Estado estado;
    double precio;
    double metrosCuadrados;
    String dniComprador;
    double UMBRAL_GRANDE = 7.0;

    // Constructor
    public Trastero(double precio, double metrosCuadrados){
        this.estado = Estado.LIBRE;
        this.precio= precio;
        this.metrosCuadrados = metrosCuadrados;
        this.dniComprador = null;

    }

    //Getters y setters

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


    // Métodos de Consulta


    public boolean esGrande(){ // ¿ Supera el umbral ?
        return metrosCuadrados > UMBRAL_GRANDE;
    }

    public boolean cumpleSuperficie(double supMin,double supMax) { // ¿ Superficie dentro del rango ?
        return metrosCuadrados >= supMin && metrosCuadrados <= supMax;
    }

    public boolean cumplePrecio(double precioMin,double precioMax ){ // ¿ Precio dentro del rango ?
        return precio >= precioMin && precio <= precioMax;
    }

    public boolean cumpleTamano(int filtroTamano){ //
        if (filtroTamano == 0){
            return true;
        }
        if (filtroTamano == 1){
            return !esGrande(); // Plazas pequeñas
        }
        if (filtroTamano == 2){
            return esGrande(); // Plazas grandes
        }
        return false;
    }

    public boolean estaDisponible(){
        return this.estado == Estado.LIBRE;
    }



    // Métodos de Transacción

    public boolean vender(String dniComprador){
        if (dniComprador == null || dniComprador.isEmpty()){
            return false;
        }
        else {
            estado = Estado.VENDIDO;
            this.dniComprador = dniComprador;
            return true;
        }
    }

    public void liberar(){
        this.estado = Estado.LIBRE;
        this.dniComprador = null;
    }


    // Métodos de Representación

    @Override
    public String toString() {
        return switch (this.estado) {
            case LIBRE -> "L";
            case VENDIDO -> "V";
        };
    }

    public String getDetalles() {
        String tamano ;

        if(esGrande()){
            tamano = "GRANDE";
        } else{
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




