package modelo;

import java.util.Random;

public class Edificio {

    private static final long SEMILLA = 12345L;
    public static final int PLANTAS_GARAJE = 2;
    private final Random rand = new Random(SEMILLA);
    public String nombre;
    public Vivienda[][] viviendas;
    public int numPlantas;
    public int viviendasPorPlanta;
    public PlazaGaraje[][] garaje;
    public int plazasPorPlantaGaraje;
    public Trastero[] trasteros;
    public int numTrasteros;


    public Edificio(String nombre, int numPlantas, int viviendasPorPlanta, int plazasPorPlantaGaraje, int numTrasteros) {
        this.nombre = nombre;
        this.numPlantas = numPlantas;
        this.viviendasPorPlanta = viviendasPorPlanta;
        this.plazasPorPlantaGaraje = plazasPorPlantaGaraje;
        this.numTrasteros = numTrasteros;

        this.viviendas = new Vivienda[numPlantas][viviendasPorPlanta];
        this.garaje = new PlazaGaraje[2][plazasPorPlantaGaraje];
        this.trasteros = new Trastero[numTrasteros];

        generarViviendasAleatorias();
        generarGarajeAleatorio();
        generarTrasterosAleatorios();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.isEmpty()) {
            System.out.println("ERROR: Nombre no puede ser vacío");
            return;
        }
        this.nombre = nombre;
    }

    public int getNumPlantas() {
        return numPlantas;
    }

    public int getViviendasPorPlanta() {
        return viviendasPorPlanta;
    }

    public int getPlantasGaraje() {
        return PLANTAS_GARAJE;
    }

    public int getPlazasPorPlantaGaraje() {
        return plazasPorPlantaGaraje;
    }

    public int getNumTrasteros() {
        return numTrasteros;
    }

    public Vivienda getVivienda(int planta, int puerta) {
        if (planta < 0 || puerta < 0 || planta >= numPlantas || puerta >= viviendasPorPlanta) {
            System.out.println("ERROR: Planta o puerta no esta valida");
            return null;
        }
        for (int i = 0; i <= planta; i++) {
            if (this.viviendasPorPlanta == planta && this.viviendas[i][puerta] != null) {
                return this.viviendas[planta][puerta];
            }
        }
        System.out.println("ERROR: Vivienda no existe");
        return null;
    }

    public PlazaGaraje getPlazaGaraje(int sotano, int plaza) {
        if (sotano < 0 || plaza < 0 || plaza >= this.plazasPorPlantaGaraje || sotano >= PLANTAS_GARAJE) {
            System.out.println("ERROR: sótano o plaza no están validos");
            return null;
        }
        if (garaje[sotano][plaza] == null) {
            System.out.println("ERROR: Garaje no existe");
        }
        return this.garaje[sotano][plaza];
    }

    public Trastero getTrastero(int indice) {
        if (indice < 0 || indice >= this.numTrasteros || this.trasteros[indice] == null) {
            System.out.println("ERROR: Trastero no existe");
            return null;
        }
        return this.trasteros[indice];
    }

    public void setVivienda(int planta, int puerta, Vivienda vivienda) {
        if (this.getVivienda(planta, puerta) != null &&
                (planta >= this.numPlantas || planta < 0) &&
                (puerta >= this.viviendasPorPlanta || puerta < 0)) {
            System.out.println("ERROR: Vivienda existe en esta puerta o planta o puerta no están validas");
            return;
        }
        viviendas[planta][puerta] = vivienda;
    }

    public void mostrarEstado() {
        System.out.println("Edificio residencial " + nombre);
        for (int i = 0; i < viviendasPorPlanta; i++) {
            System.out.printf("   P" + (i + 1));

        }

        System.out.println(" ");

        for (int planta = numPlantas; planta >= 0; planta--) {
            System.out.println("Planta" + planta);

            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null) {
                    switch (vivienda.estado) {
                        case LIBRE:
                            System.out.println("[L]");
                            break;
                        case RESERVADO:
                            System.out.println("[R]");
                            break;
                        case VENDIDO:
                            System.out.println("[V]");
                            break;
                    }

                }

            }
        }

    }

    public void mostrarEdificio() {
        mostrarEstado();

        System.out.println("[GARAJE]");
        for (int i = 0; i < plazasPorPlantaGaraje; i++) {
            System.out.printf("   P" + (i + 1));
        }


        for (int planta = 1; planta < 2; planta++) {

            System.out.println("Sotano " + (planta - 2));
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje plazaGaraje = garaje[planta][plaza];
                if (plazaGaraje != null) {
                    switch (plazaGaraje.estado) {
                        case LIBRE:
                            System.out.println("[L]");
                            break;
                        case VENDIDO:
                            System.out.println("[V]");
                            break;
                    }
                }
            }
        }

        System.out.println("[TRASTERO]");
        for (int i = 0; i < numTrasteros; i++) {
            System.out.printf("   T" + (i + 1));
        }

        for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
            Trastero trastero = trasteros[plaza];
            if (trastero != null) {
                switch (trastero.estado) {
                    case LIBRE:
                        System.out.println("[L]");
                        break;
                    case VENDIDO:
                        System.out.println("[V]");
                        break;
                }
            }

        }
    }


    // Union viviendas
    public boolean puedenUnirseViviendas(int planta, int puerta1, int puerta2){
        return true;
    }
    public boolean unirViviendas(int planta, int puerta1, int puerta2, String dni, Vivienda.Calidad calidad){
        return true;
    }

    // Union trasteros
    public boolean puedenUnirseTrasteros(int trastero1, int trastero2){
        return true;
    }
    public boolean unirTrasteros(int trastero1, int trastero2, String dniComprador){
        return true;
    }


    // Metodos de Conteo
    public int contarViviendasPorDni(String dni) {
        int contar = 0;
        if (dni == null || dni.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < viviendas.length; i++) {
                for (int j = 0; j < viviendas[0].length; j++) {
                    if (viviendas[i][j] != null) {
                        if(viviendas[i][j].getEstado() == Vivienda.Estado.VENDIDO && dni.equalsIgnoreCase(viviendas[i][j].getDniComprador())){
                            contar += 1;
                        }
                    }
                }
            }
        }
        return contar;
    }

    public int contarPlazasPorDni(String dni){
        int contar = 0;
        if (dni == null || dni.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < garaje.length; i++) {
                for (int j = 0; j < garaje[0].length; j++) {
                    if (garaje[i][j] != null) {
                        if(garaje[i][j].getEstado() == PlazaGaraje.Estado.VENDIDO && dni.equalsIgnoreCase(garaje[i][j].getDniComprador())){
                            contar += 1;
                        }
                    }
                }
            }
        }
        return contar;

    }


    public int contarTrasterosPorDni(String dni){
        int contar = 0;
        if (dni == null || dni.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < trasteros.length; i++) {
                if (trasteros[i] != null) {
                    if(trasteros[i].getEstado() == Trastero.Estado.VENDIDO && dni.equalsIgnoreCase(trasteros[i].getDniComprador())) {
                        contar += 1;
                    }
                }
            }
        }
        return contar;
    }

    // Metodos de Listado
    public String listarViviendasPorDni(String dni) {
        String detalles="";
        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        } else {
            for (int i = 0; i < viviendas.length; i++) {
                for (int j = 0; j < viviendas[0].length; j++) {
                    if (viviendas[i][j] != null) {
                        if (viviendas[i][j].getEstado() == Vivienda.Estado.VENDIDO && dni.equalsIgnoreCase(viviendas[i][j].getDniComprador())) {
                            detalles += viviendas[i][j].getDetalles();
                        }
                    }
                }
            }
            if (detalles.isEmpty()){
                detalles = "No hay detalles";
            }
            return detalles;
        }
    }

    public String listarPlazasPorDni(String dni){
        String detalles="";
        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        } else {
            for (int i = 0; i < garaje.length; i++) {
                for (int j = 0; j < garaje[0].length; j++) {
                    if (garaje[i][j] != null) {
                        if (garaje[i][j].getEstado() == PlazaGaraje.Estado.VENDIDO && dni.equalsIgnoreCase(garaje[i][j].getDniComprador())) {
                            detalles += garaje[i][j].getDetalles();
                        }
                    }
                }
            }
            if (detalles.isEmpty()){
                detalles = "No hay detalles";
            }
            return detalles;
        }
    }

    public String listarTrasterosPorDni(String dni){
        String detalles="";
        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        } else {
            for (int i = 0; i < trasteros.length; i++) {
                if (trasteros[i] != null) {
                    if (trasteros[i].getEstado() == Trastero.Estado.VENDIDO && dni.equalsIgnoreCase(trasteros[i].getDniComprador())) {
                            detalles += trasteros[i].getDetalles();
                    }
                }
            } if (detalles.isEmpty()){
                detalles = "No hay detalles";
            }
            return detalles;

    }

    public void generarViviendasAleatorias() {
        for (int i = 0; i < viviendas.length; i++) {
            for (int j = 0; j < viviendas[0].length; j++) {
                viviendas[i][j] = new Vivienda(
                        80000 + (i * 10000) + rand.nextInt(0, 120001),
                        rand.nextInt(40, 181),
                        rand.nextInt(1, 6));
            }
        }
    }

    public void generarGarajeAleatorio() {
        for (int i = 0; i < garaje.length; i++) {
            for (int j = 0; j < garaje[0].length; j++) {
                garaje[i][j] = new PlazaGaraje(
                        rand.nextInt(8000, 30001),
                        rand.nextInt(8, 21));
            }
        }
    }

    private void generarTrasterosAleatorios() {
        for (int i = 0; i < trasteros.length; i++) {
            trasteros[i] = new Trastero(
                    rand.nextInt(1500, 8001),
                    rand.nextInt(3, 16));
        }
    }
}
