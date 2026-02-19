package modelo;

enum Estado_VIVIENDA {
    LIBRE,
    RESERVADO,
    VENDIDO
}
enum Calidad_VIVIENDA {
    ESTANDAR,
    PLUS,
    DE_LUXE

}

public class Vivienda {

     Estado_VIVIENDA estado = Estado_VIVIENDA.RESERVADO;
     double precio;
     double metrosCuadrados;
     int habitaciones;
     String dniComprador = null;
     Calidad_VIVIENDA calidad = null;

    public Vivienda(double precio, double metrosCuadrados, int habitaciones) {
        this.precio = precio;
        this.metrosCuadrados = metrosCuadrados;
        this.habitaciones = habitaciones;
    }

    public Estado_VIVIENDA getEstado() {
        return estado;
    }

    public void setEstado(Estado_VIVIENDA estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return switch (calidad) {
            case ESTANDAR -> precio;
            case PLUS -> precio * 1.05;
            case DE_LUXE -> precio * 1.10;
            default -> precio;
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

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        if (metrosCuadrados < 1) {
            System.out.println("ERROR: MetrosCuadrados tiene que ser mayor o igual a 1");
            return;
        }
        this.metrosCuadrados = metrosCuadrados;
    }

    public Calidad_VIVIENDA getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad_VIVIENDA calidad) {
        this.calidad = calidad;
    }

    public double getPrecioBase() {
        return precio;
    }
}