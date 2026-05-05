package modelo;

public class PlazaGaraje {

    //defino las variables
    double precio = 0;
    double metrosCuadrados = 0;
    String dniComprador;
    double UMBRAL_GRANDE = 12.0;
    Estado estado;
    //contructor
    public PlazaGaraje(double precio, double metrosCuadrados) {
        this.estado = Estado.LIBRE;
        this.precio = precio;
        this.metrosCuadrados = metrosCuadrados;
        dniComprador = null;

    }

    public Estado getEstado() {
        return estado;
    }

    //Getter y setter

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public String getDniComprador() {
        return dniComprador;
    }

    public void setDniComprador(String dniComprador) {
        this.dniComprador = dniComprador;
    }

    public boolean esGrande() {
        return metrosCuadrados > UMBRAL_GRANDE;
    }


    //Hago metodos/funciones

    public boolean cumpleSuperficie(double supMin, double supMax) {
        return metrosCuadrados >= supMin && metrosCuadrados <= supMax;
    }

    public boolean cumplePrecio(double precioMin, double precioMax) {
        return precio >= precioMin && precio <= precioMax;
    }

    public boolean cumpleTamano(int filtroTamano) {
        if (filtroTamano == 0) {
            return true;
        }
        if (filtroTamano == 1) {
            return !esGrande();
        }
        if (filtroTamano == 2) {
            return esGrande();
        }
        return false;

    }

    public boolean vender(String dniComprador) {
        if (dniComprador == null || dniComprador.isEmpty()) {
            return false;
        } else {
            estado = Estado.VENDIDO;
            this.dniComprador = dniComprador;
            return true;
        }
    }


    //metodos/funcion de transacciones

    public void liberar() {
        estado = Estado.LIBRE;
        dniComprador = null;
    }

    public boolean estaDisponible() {
        return estado == Estado.LIBRE;
    }

    @Override
    public String toString() {
        return switch (this.estado) {
            case LIBRE -> "L";
            case VENDIDO -> "V";
        };
    }

    public String getDetalles() {
        String tamano;

        if (esGrande()) {
            tamano = "GRANDE";
        } else {
            tamano = "PEQUEÑA";
        }

        return "DETALLES :" +
                "\nESTADO -> " + estado +
                "\nTAMAÑO ->" + tamano +
                "\nPRECIO -> " + precio +
                "\nMETROS^2 -> " + metrosCuadrados +
                "\nDNI COMPRADOR ->" + dniComprador;
    }

    //Enumero el estado
    public enum Estado {
        LIBRE, VENDIDO
    }
}


