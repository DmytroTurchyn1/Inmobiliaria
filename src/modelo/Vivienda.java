package modelo;

public class Vivienda {

    public enum Estado {
        LIBRE,
        RESERVADO,
        VENDIDO
    }

    public enum Calidad {
        ESTANDAR,
        PLUS,
        DE_LUXE

    }

    Estado estado;
     double precio;
     double metrosCuadrados;
     int habitaciones;
    String dniComprador;
    Calidad calidad;

    public Vivienda(double precio, double metrosCuadrados, int habitaciones) {
        this.precio = precio;
        this.metrosCuadrados = metrosCuadrados;
        this.habitaciones = habitaciones;
        this.estado = Estado.LIBRE;
        this.dniComprador = null;
        this.calidad = null;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return switch (calidad) {
            case ESTANDAR -> this.precio;
            case PLUS -> precio * 1.05;
            case DE_LUXE -> precio * 1.10;
            case null -> precio;
        };
    }

    public void setPrecio(double precio) {
        if (precio < 1) {
            System.out.println("ERROR: Precio tiene que ser mayor o igual a 1");
            return;
        }
        this.precio = precio;
    }

    public String getDniComprador() {
        return dniComprador;
    }

    public void setDniComprador(String dniComprador) {
        if (dniComprador == null) {
            System.out.println("ERROR: DNI no puede ser null");
            return;
        }
        this.dniComprador = dniComprador;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        if (habitaciones < 1) {
            System.out.println("ERROR: Numero de habitaciones tiene que ser mayor o igual a 1");
            return;
        }
        this.habitaciones = habitaciones;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        if (metrosCuadrados < 1) {
            System.out.println("ERROR: MetrosCuadrados tiene que ser mayor o igual a 1");
            return;
        }
        this.metrosCuadrados = metrosCuadrados;
    }



    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public Calidad getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }

    public double getPrecioBase() {
        return precio;
    }


    public boolean cumpleSuperficie(double metrosMin, double metrosMax) {
        return metrosCuadrados > metrosMin && metrosCuadrados < metrosMax;
    }

    public boolean cumplePrecio(double precioMin, double precioMax) {
        return this.getPrecio() > precioMin && this.getPrecio() < precioMax;
    }

    public boolean cumpleHabitaciones(int habMin, int habMax) {
        return this.habitaciones > habMin && habitaciones < habMax;
    }

    public boolean estaDisponible() {
        return estado == Estado.LIBRE;
    }

    public boolean vender(String dni, Calidad calidad) {
        if (dni == null || dni.isBlank()) {
            return false;
        }
        if (!this.estaDisponible()) {
            return false;
        } else {
            this.estado = Estado.VENDIDO;
            this.calidad = calidad;
            this.dniComprador = dni;
            return true;
        }
    }

    public boolean vender(String dni) {
        if (dni == null || dni.isBlank()) {
            return false;
        } else return vender(dni, Calidad.ESTANDAR);
    }

    public boolean reservar(String dni, Calidad calidad) {
        if (dni == null || dni.isBlank()) {
            return false;
        }
        if (!this.estaDisponible()) {
            return false;
        } else {
            this.estado = Estado.RESERVADO;
            this.dniComprador = dni;
            this.calidad = calidad;
            return true;
        }
    }

    public boolean reservar(String dni) {
        if (dni == null || dni.isBlank()) {
         return false;
        } else return reservar(dni, Calidad.ESTANDAR);
    }

    public void liberar() {
        this.estado = Estado.LIBRE;
        this.dniComprador = null;
        this.calidad = null;
    }



    @Override
    public String toString() {
        return switch (this.estado) {
            case LIBRE -> "L";
            case RESERVADO -> "R";
            case VENDIDO -> "V";
        };
    }

    public String getDetalles() {
        return "\nHabitaciones: " + this.habitaciones +
                "\nCalidad: " + this.calidad +
                "\nEstado: " + this.estado +
                "\nDNI: " + this.dniComprador +
                "\nMetros Cuadrados: " + this.metrosCuadrados +
                "\nPrecio: " + this.getPrecio();
    }
}