package modelo;

import java.util.Arrays;

public class Promotora {
    private final String nombre;
    private Edificio[] edificios;
    private int numEdificios;

    public Promotora(String nombre) {
        edificios = new Edificio[3];
        this.nombre = nombre;
        edificios[0] = new Edificio("Edificio 1", 2, 3, 10, 3);
        edificios[1] = new Edificio("Edificio 2", 3, 5, 7, 5);
        this.numEdificios = 2;
    }

    public void agregarEdificio(Edificio edificio) {
        if (numEdificios == edificios.length) {
            edificios = Arrays.copyOf(edificios, edificios.length * 2);
        }
        edificios[numEdificios] = edificio;
        numEdificios++;
    }

    public Edificio getEdificio(int indice) {
        if (indice < numEdificios && indice >= 0) {
            return edificios[indice];
        }
        System.out.println("Indice no valido");
        return null;
    }

    public Edificio[] getEdificios() {
        return Arrays.copyOf(edificios, numEdificios);
    }

    public int getNumeroEdificios() {
        return numEdificios;
    }

    public String getNombre() {
        return nombre;
    }

    public void listarEdificios() {
        for (int i = 0; i < numEdificios; i++) {
            System.out.println(i + " - " + edificios[i].getNombre());
        }
    }

    public boolean venderVivienda(int indiceEdificio, int planta, int puerta, String dni, Vivienda.Calidad calidad) {
        Vivienda vivienda = verificarVivienda(indiceEdificio, planta, puerta);
        if (vivienda == null) return false;
        if (vivienda.vender(dni, calidad)) {
            return true;
        }

        System.out.println("Error al vender la vivienda");
        return false;
    }

    public boolean reservarVivienda(int indiceEdificio, int planta, int puerta, String dni, Vivienda.Calidad calidad) {
        Vivienda vivienda = verificarVivienda(indiceEdificio, planta, puerta);
        if (vivienda == null) return false;

        if (vivienda.reservar(dni, calidad)) {
            return true;
        }

        System.out.println("Error al reservar la vivienda");
        return false;
    }

    public boolean venderPlazaGaraje(int indiceEdificio, int sotano, int plaza, String dni) {
        Edificio edificio = verificarEdificio(indiceEdificio);
        if (edificio == null) return false;
        PlazaGaraje plazaGaraje = edificio.getPlazaGaraje(sotano, plaza);
        if (plazaGaraje == null) {
            System.out.println("No existe la plaza de garaje");
            return false;
        }
        if (!plazaGaraje.estaDisponible()) {
            System.out.println("Plaza de garaje no esta disponible");
            return false;
        }
        if (plazaGaraje.vender(dni)) {
            return true;
        }

        System.out.println("Error al vender la plaza de garaje");
        return false;


    }

    public boolean venderTrastero(int indiceEdificio, int indice, String dni) {

        Edificio edificio = verificarEdificio(indiceEdificio);
        if (edificio == null) return false;

        Trastero trastero = edificio.getTrastero(indice);

        if (trastero == null) {
            System.out.println("No existe el trastero");
            return false;
        }

        if (!trastero.estaDisponible()) {
            System.out.println("Trastero no esta disponible");
            return false;
        }

        if (trastero.vender(dni)) {
            return true;
        }

        System.out.println("Error al vender el trastero");
        return false;

    }

    private Vivienda verificarVivienda(int indiceEdificio, int planta, int puerta) {
        Edificio edificio = verificarEdificio(indiceEdificio);
        if (edificio == null) return null;
        Vivienda vivienda = edificio.getVivienda(planta, puerta);
        if (vivienda == null) {
            System.out.println("No existe la vivienda");
            return null;
        }
        return vivienda;
    }

    private Edificio verificarEdificio(int indiceEdificio) {
        Edificio edificio = getEdificio(indiceEdificio);
        if (edificio == null) {
            System.out.println("No existe el edificio");
            return null;
        }
        return edificio;
    }

    // FASE C

    public void mostrarEstadisticasGenerales() {
        int totalViviendas = 0;
        int libresV = 0;
        int reservadasV = 0;
        int vendidasV = 0;
        double ingresosPotencialesV = 0.0;
        double ingresosRealesV = 0.0;

        int totalPlazas = 0;
        int libresP = 0;
        int vendidasP = 0;
        double ingresosPotencialesP = 0.0;
        double ingresosRealesP = 0.0;

        int totalTrasteros = 0;
        int libresT = 0;
        int vendidosT = 0;
        double ingresosPotencialesT = 0.0;
        double ingresosRealesT = 0.0;

        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                totalViviendas += e.getTotalViviendas();
                libresV += e.contarViviendasLibres();
                reservadasV += e.contarViviendasReservadas();
                vendidasV += e.contarViviendasVendidas();
                ingresosPotencialesV += e.calcularIngresosPotenciales();
                ingresosRealesV += e.calcularIngresosVendidos();

                totalPlazas += e.getTotalPlazasGaraje();
                libresP += e.contarPlazasGarajeLibres();
                vendidasP += e.contarPlazasGarajeVendidas();
                ingresosPotencialesP += e.calcularIngresosPotencialesPlazaGaraje();
                ingresosRealesP += e.calcularIngresosVendidosPlazaGaraje();

                totalTrasteros += e.getTotalTrasteros();
                libresT += e.totalTrasterosLibres();
                vendidosT += e.totalTrasterosVendidos();
                ingresosPotencialesT += e.calcularIngresosPotencialesTrasteros();
                ingresosRealesT += e.calcularIngresosVendidosTrasteros();
            }
        }

        System.out.println("\n--- VIVIENDAS ---");
        System.out.printf("Total: %d | Libres: %d | Reservadas: %d | Vendidas: %d\n", totalViviendas, libresV, reservadasV, vendidasV);
        System.out.printf("Ingresos potenciales: %.2f€ | Ingresos reales: %.2f€\n", ingresosPotencialesV, ingresosRealesV);
        System.out.println("\n--- PLAZAS DE GARAJE ---");
        System.out.printf("Total: %d | Libres: %d | Vendidas: %d\n", totalPlazas, libresP, vendidasP);
        System.out.printf("Ingresos potenciales: %.2f€ | Ingresos reales: %.2f€\n", ingresosPotencialesP, ingresosRealesP);
        System.out.println("\n--- TRASTEROS ---");
        System.out.printf("Total: %d | Libres: %d | Vendidos: %d\n", totalTrasteros, libresT, vendidosT);
        System.out.printf("Ingresos potenciales: %.2f€ | Ingresos reales: %.2f€\n", ingresosPotencialesT, ingresosRealesT);
    }

    public void listarPropiedadesPorDni(String dni) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                String viviendasDni = e.listarViviendasPorDni(dni);
                if (!viviendasDni.equals("No hay detalles")) {
                    System.out.println(viviendasDni);
                }
                String plazasDni = e.listarPlazasPorDni(dni);
                if (!plazasDni.equals("No hay detalles")) {
                    System.out.println(plazasDni);
                }
                String trasterosDni = e.listarTrasterosPorDni(dni);
                if (!trasterosDni.equals("No hay detalles")) {
                    System.out.println(trasterosDni);
                }
            }
        }
    }

//FASE D

    public void buscarViviendasPorSuperficie(double metrosMin, double metrosMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarViviendasPorSuperficie(metrosMin, metrosMax);
            }
        }
    }

    public void buscarViviendasPorPrecio(double precioMin, double precioMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarViviendasPorPrecio(precioMin, precioMax);
            }
        }
    }

    public void buscarViviendasPorHabitaciones(int habMin, int habMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarViviendasPorHabitaciones(habMin, habMax);
            }
        }
    }

    public void buscarViviendas(double metrosMin, double metrosMax, double precioMin, double precioMax, int habMin, int habMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarViviendas(metrosMin, metrosMax, precioMin, precioMax, habMin, habMax);
            }
        }
    }

    public void buscarPlazasGarajePorSuperficie(double metrosMin, double metrosMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarPlazasGarajePorSuperficie(metrosMin, metrosMax);
            }
        }
    }

    public void buscarPlazasGarajePorPrecio(double precioMin, double precioMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarPlazasGarajePorPrecio(precioMin, precioMax);
            }
        }
    }

    public void buscarPlazasGarajePorTamano(int filtroTamano) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarPlazasGarajePorTamano(filtroTamano);
            }
        }
    }

    public void buscarPlazasGaraje(double metrosMin, double metrosMax, double precioMin, double precioMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarPlazasGaraje(metrosMin, metrosMax, precioMin, precioMax);
            }
        }
    }

    public void buscarTrasterosPorSuperficie(double metrosMin, double metrosMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarTrasterosPorSuperficie(metrosMin, metrosMax);
            }
        }
    }

    public void buscarTrasterosPorPrecio(double precioMin, double precioMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarTrasterosPorPrecio(precioMin, precioMax);
            }
        }
    }

    public void buscarTrasterosPorTamano(int filtroTamano) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarTrasterosPorTamano(filtroTamano);
            }
        }
    }

    public void buscarTrasteros(double metrosMin, double metrosMax, double precioMin, double precioMax) {
        for (int i = 0; i < numEdificios; i++) {
            Edificio e = edificios[i];
            if (e != null) {
                e.buscarTrasteros(metrosMin, metrosMax, precioMin, precioMax);
            }
        }
    }
}
