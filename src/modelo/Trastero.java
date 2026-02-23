package modelo;

public class Trastero {

    enum Estado {LIBRE,VENDIDO;

        public Estado libre() {
        }
    }
    Estado estado;
    double precio;
    double metrosCuadrados;
    String dniComprador;
    double UMBRAL_GRANDE=7.0;


    public Trastero(double precio, double metrosCuadrados) {
        this.estado=Estado.LIBRE;
        this.dniComprador=null;
        this.precio = precio;
        this.metrosCuadrados = metrosCuadrados;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public double getPrecio() {
        return precio;
    }


    public Estado getEstado() {
        return estado;
    }

    public String getDniComprador() {
        return dniComprador;
    }

    public void setDniComprador(String dniComprador) {
        this.dniComprador = dniComprador;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    boolean esGrande(){
        //*ver Verifica si la plaza supera el umbral de 7.0 m²
        //• Retorna: boolean (true si metrosCuadrados > 7.0)*//
        if (this.metrosCuadrados > UMBRAL_GRANDE){
            return true;
        }
        else{
            return false;
        }
    }
    Void cumpleSuperficie(double metrosMin,double metrosMax){


    }
    void cumplePrecio(double precioMin,double precioMax){

    }
    void cumpleTamano(int filtroTamano){
        if(filtroTamano = 0){

        }
        if(filtroTamano = 1){

        }
        if (filtroTamano = 2){

        }
    }
    Estado estaDisponible(){
        if (this.estado=Estado.libre()){
            return this.getEstado();
        }
        else {
            return this.getEstado();
        }
    }
    boolean vender(String dni){
        if (dni!=null){
            this.setEstado(Estado.VENDIDO);
            return true;
        }
        else {
            return false;
        }
    }
    void liberar(){
        this.setEstado(Estado.LIBRE);
        this.setDniComprador(null);
    }


}



