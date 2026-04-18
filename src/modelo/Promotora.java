package modelo;

import java.util.Arrays;

public class Promotora {
    private String nombre;
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
        edificios[numEdificios - 1] = edificio;
        numEdificios++;
    }

    public Edificio getEdificio(int indice) {
        if (indice <= numEdificios && indice >= 0) {
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

    public boolean venderTrastero(int indiceEdificio, int indice, int plaza, String dni) {

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
}
