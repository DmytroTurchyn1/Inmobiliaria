package modelo;

import java.util.Random;

public class Edificio {

    private static final long SEMILLA = 12345L;
    public final int PLANTAS_GARAJE = 2;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPlantas() {
        return numPlantas;
    }

    public int getViviendasPorPlanta() {
        return viviendasPorPlanta;
    }

    public int getPlantasGaraje() {
        return 2;
    }

    public int getPlazasPorPlantaGaraje() {
        return plazasPorPlantaGaraje;
    }

    public int getNumTrasteros() {
        return numTrasteros;
    }

    public Vivienda getVivienda(int planta, int puerta) {
        for (int i = 0; i < planta; i++) {
            if (this.viviendasPorPlanta == planta) {
                return this.viviendas[planta][puerta];
            } else {
                System.out.println("ERROR: Vivienda no existe");
                break;
            }
        }
        return null;
    }

    public void setVivienda(int planta, int puerta, Vivienda vivienda) {

    }

    public void generarViviendasAleatorias() {
        for (int i = 0; i < viviendas.length; i++) {
            for (int j = 0; j < viviendas[1].length; j++) {
                viviendas[i][j] = new Vivienda(
                        80000 + (i * 10000) + rand.nextInt(0, 12000),
                        rand.nextInt(40, 100),
                        rand.nextInt(1, 5));
            }
        }
    }

    public void generarGarajeAleatorio() {
        for (int i = 0; i < garaje.length; i++) {
            for (int j = 0; j < garaje[1].length; j++) {
                garaje[i][j] = new PlazaGaraje(
                        rand.nextInt(8000, 30000),
                        rand.nextInt(8, 20));
            }
        }
    }

    private void generarTrasterosAleatorios() {
        for (int i = 0; i < trasteros.length; i++) {
            trasteros[i] = new Trastero(
                    rand.nextInt(1500, 8000),
                    rand.nextInt(3, 15));
        }
    }


}
