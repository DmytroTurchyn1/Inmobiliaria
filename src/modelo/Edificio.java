package modelo;

import java.util.Random;

public class Edificio {

    private static final long SEMILLA = 12345L;
    private static final int PLANTAS_GARAJE = 2;
    private final Random rand = new Random(SEMILLA);
    private String nombre;
    private final Vivienda[][] viviendas;
    private final int numPlantas;
    private final int viviendasPorPlanta;
    private final PlazaGaraje[][] garaje;
    private final int plazasPorPlantaGaraje;
    private final Trastero[] trasteros;
    private final int numTrasteros;


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
        if (nombre == null || nombre.isEmpty()) {
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
        if (viviendas[planta][puerta] == null) {
            System.out.println("ERROR: Vivienda no existe");
            return null;
        }
        return this.viviendas[planta][puerta];
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
        if (planta >= numPlantas ||
                planta < 0 ||
                puerta >= viviendasPorPlanta ||
                puerta < 0) {
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

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            System.out.println("Planta" + planta);

            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null) {
                    switch (vivienda.estado) {
                        case LIBRE:
                            System.out.print("  [L]");
                            break;
                        case RESERVADO:
                            System.out.print("  [R]");
                            break;
                        case VENDIDO:
                            System.out.print("  [V]");
                            break;
                    }

                }


            }

            System.out.println();

        }
    }

    public void mostrarMatrizEdificio() {
        mostrarEstado();

        System.out.println("[GARAJE]");
        for (int i = 0; i < plazasPorPlantaGaraje; i++) {
            System.out.printf("   P" + (i + 1));
        }

        System.out.println(" ");

        for (int planta = 0; planta < 2; planta++) {

            System.out.println("Sotano " + (planta - 2));
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje plazaGaraje = garaje[planta][plaza];
                if (plazaGaraje != null) {
                    switch (plazaGaraje.estado) {
                        case LIBRE:
                            System.out.print("  [L]");
                            break;
                        case VENDIDO:
                            System.out.print("  [V]");
                            break;
                    }
                }

            }
            System.out.println();
        }


        System.out.println("[TRASTERO]");
        for (int i = 0; i < numTrasteros; i++) {
            System.out.printf("   T" + (i + 1));
        }

        System.out.println(" ");

        for (int plaza = 0; plaza < numTrasteros; plaza++) {
            Trastero trastero = trasteros[plaza];
            if (trastero != null) {
                switch (trastero.estado) {
                    case LIBRE:
                        System.out.print("  [L]");
                        break;
                    case VENDIDO:
                        System.out.print("  [V]");
                        break;
                }
            }

        }

    }

    public int getTotalViviendas() {
        int contadorTotViviendas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null) {
                    contadorTotViviendas++;

                }

            }
        }

        return contadorTotViviendas;
    }

    public int contarViviendasLibres() {
        int contadorVivLibres = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null && vivienda.estado == Vivienda.Estado.LIBRE) {
                    contadorVivLibres++;
                }
            }
        }

        return contadorVivLibres;
    }

    public int contarViviendasReservadas() {
        int contadorVivReservadas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null && vivienda.estado == Vivienda.Estado.RESERVADO) {
                    contadorVivReservadas++;
                }
            }
        }

        return contadorVivReservadas;
    }

    public int contarViviendasVendidas() {
        int contadorVivVendidas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null && vivienda.estado == Vivienda.Estado.VENDIDO) {
                    contadorVivVendidas++;
                }
            }
        }

        return contadorVivVendidas;
    }

    public int getTotalPlazasGaraje() {
        int totalPlazasGaraje = 0;

        for (int planta = 0; planta < 2; planta++) {
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++) {
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if (plazaGaraje != null) {
                    totalPlazasGaraje++;
                }
            }
        }

        return totalPlazasGaraje;
    }

    public int contarPlazasGarajeVendidas() {
        int plazasGarajeVendidas = 0;

        for (int planta = 0; planta < 2; planta++) {
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++) {
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if (plazaGaraje != null && plazaGaraje.estado == PlazaGaraje.Estado.VENDIDO) {
                    plazasGarajeVendidas++;
                }
            }
        }

        return plazasGarajeVendidas;
    }

    public int contarPlazasGarajeLibres() {
        int plazasGarajeLibres = 0;

        for (int planta = 0; planta < 2; planta++) {
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++) {
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if (plazaGaraje != null && plazaGaraje.estado == PlazaGaraje.Estado.LIBRE) {
                    plazasGarajeLibres++;
                }
            }
        }

        return plazasGarajeLibres;
    }

    public int getTotalTrasteros() {
        int numTotalTrasteros = 0;

        for (int puerta = 0; puerta < numTrasteros; puerta++) {
            Trastero trastero = trasteros[puerta];
            if (trastero != null) {
                numTotalTrasteros++;
            }
        }

        return numTotalTrasteros;
    }

    public int totalTrasterosVendidos() {
        int trasterosVendidos = 0;

        for (int puerta = 0; puerta < numTrasteros; puerta++) {
            Trastero trastero = trasteros[puerta];
            if (trastero != null && trastero.estado == Trastero.Estado.VENDIDO) {
                trasterosVendidos++;
            }
        }

        return trasterosVendidos;
    }

    public int totalTrasterosLibres() {
        int trasterosLibres = 0;

        for (int puerta = 0; puerta < numTrasteros; puerta++) {
            Trastero trastero = trasteros[puerta];
            if (trastero != null && trastero.estado == Trastero.Estado.LIBRE) {
                trasterosLibres++;
            }
        }

        return trasterosLibres;
    }


    //Cálculos

    public double calcularIngresosPotenciales() {
        double ingresoPotencial = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null) {
                    ingresoPotencial += vivienda.getPrecio();
                }

            }
        }

        return ingresoPotencial;

    }

    public double calcularIngresosVendidos() {
        double ingresoVivVendidas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null && vivienda.estado == Vivienda.Estado.VENDIDO) {
                    ingresoVivVendidas += vivienda.getPrecio();
                }
            }
        }

        return ingresoVivVendidas;
    }

    public double calcularIngresosPotencialesPlazaGaraje() {
        double ingresoPotencialPlazaGaraje = 0;

        for (int planta = 0; planta < 2; planta++) {
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++) {
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if (plazaGaraje != null) {
                    ingresoPotencialPlazaGaraje += plazaGaraje.getPrecio();
                }
            }
        }

        return ingresoPotencialPlazaGaraje;
    }

    public double calcularIngresosVendidosPlazaGaraje() {
        double ingresoVendidosPLazas = 0;

        for (int planta = 0; planta < 2; planta++) {
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++) {
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if (plazaGaraje != null && plazaGaraje.estado == PlazaGaraje.Estado.VENDIDO) {
                    ingresoVendidosPLazas += plazaGaraje.getPrecio();
                }
            }
        }

        return ingresoVendidosPLazas;
    }

    public double calcularIngresosPotencialesTrasteros() {
        double ingresoPotencialesTrasteros = 0;

        for (int puerta = 0; puerta < numTrasteros; puerta++) {
            Trastero trastero = trasteros[puerta];
            if (trastero != null) {
                ingresoPotencialesTrasteros += trastero.getPrecio();
            }
        }

        return ingresoPotencialesTrasteros;
    }


    public double calcularIngresosVendidosTrasteros() {
        double ingresoTrasterosVendidos = 0;

        for (int puerta = 0; puerta < numTrasteros; puerta++) {
            Trastero trastero = trasteros[puerta];
            if (trastero != null && trastero.estado == Trastero.Estado.VENDIDO) {
                ingresoTrasterosVendidos += trastero.getPrecio();
            }
        }

        return ingresoTrasterosVendidos;
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

    // 7.1 Búsqueda de viviendas por superficie
    public void buscarViviendasPorSuperficie(double metrosMin, double metrosMax) {
        System.out.println("\n=== Viviendas con superficie entre " + metrosMin + " y " + metrosMax + " m2 ===");
        boolean encontrada = false;

        for (int planta = 0; planta < numPlantas; planta++) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda v = viviendas[planta][puerta];
                if (v != null && v.cumpleSuperficie(metrosMin, metrosMax) && v.estaDisponible()) {
                    System.out.println("Planta " + planta + ", Puerta " + puerta + ": " + v);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron viviendas que cumplan el criterio.");
        }
    }

    // 7.1 Búsqueda de viviendas por precio
    public void buscarViviendasPorPrecio(double precioMin, double precioMax) {
        System.out.println("\n=== Viviendas con precio entre " + precioMin + " y " + precioMax + " € ===");
        boolean encontrada = false;

        for (int planta = 0; planta < numPlantas; planta++) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda v = viviendas[planta][puerta];
                if (v != null && v.cumplePrecio(precioMin, precioMax) && v.estaDisponible()) {
                    System.out.println("Planta " + planta + ", Puerta " + puerta + ": " + v);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron viviendas que cumplan el criterio.");
        }
    }

    // 7.1 Búsqueda de viviendas por habitaciones
    public void buscarViviendasPorHabitaciones(int habMin, int habMax) {
        System.out.println("\n=== Viviendas con " + habMin + " a " + habMax + " habitaciones ===");
        boolean encontrada = false;

        for (int planta = 0; planta < numPlantas; planta++) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda v = viviendas[planta][puerta];
                if (v != null && v.cumpleHabitaciones(habMin, habMax) && v.estaDisponible()) {
                    System.out.println("Planta " + planta + ", Puerta " + puerta + ": " + v);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron viviendas que cumplan el criterio.");
        }
    }

    // 7.2 Búsqueda combinada de viviendas
    public void buscarViviendas(double metrosMin, double metrosMax, double precioMin, double precioMax, int habMin, int habMax) {
        System.out.println("\n=== Búsqueda combinada de viviendas ===");
        System.out.println("Criterios:");
        System.out.println("  - Superficie: " + metrosMin + " - " + metrosMax + " m2");
        System.out.println("  - Precio: " + precioMin + " - " + precioMax + " €");
        System.out.println("  - Habitaciones: " + habMin + " - " + habMax);
        System.out.println("Resultados:");

        boolean encontrada = false;

        for (int planta = 0; planta < numPlantas; planta++) {
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda v = viviendas[planta][puerta];
                if (v != null &&
                        v.cumpleSuperficie(metrosMin, metrosMax) &&
                        v.cumplePrecio(precioMin, precioMax) &&
                        v.cumpleHabitaciones(habMin, habMax) &&
                        v.estaDisponible())  {
                    System.out.println("Planta " + planta + ", Puerta " + puerta + ": " + v);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron viviendas que cumplan todos los criterios.");
        }
    }

    // 7.3 Búsqueda de plazas de garaje por superficie
    public void buscarPlazasGarajePorSuperficie(double metrosMin, double metrosMax) {
        System.out.println("\n=== Plazas de garaje con superficie entre " + metrosMin + " y " + metrosMax + " m2 ===");
        boolean encontrada = false;

        for (int sotano = 0; sotano < PLANTAS_GARAJE; sotano++) {
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje p = garaje[sotano][plaza];
                if (p != null && p.cumpleSuperficie(metrosMin, metrosMax)&& p.estaDisponible()) {
                    System.out.println("Sótano " + (sotano + 1) + ", Plaza " + plaza + ": " + p);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron plazas que cumplan el criterio.");
        }
    }

    // 7.3 Búsqueda de plazas de garaje por precio
    public void buscarPlazasGarajePorPrecio(double precioMin, double precioMax) {
        System.out.println("\n=== Plazas de garaje con precio entre " + precioMin + " y " + precioMax + " € ===");
        boolean encontrada = false;

        for (int sotano = 0; sotano < PLANTAS_GARAJE; sotano++) {
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje p = garaje[sotano][plaza];
                if (p != null && p.cumplePrecio(precioMin, precioMax) && p.estaDisponible()) {
                    System.out.println("Sótano " + (sotano + 1) + ", Plaza " + plaza + ": " + p);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron plazas que cumplan el criterio.");
        }
    }

    // 7.3 Búsqueda de plazas de garaje por tamaño (0: todas, 1: pequeñas, 2: grandes)
    public void buscarPlazasGarajePorTamano(int filtroTamano) {
        String tipo = "";
        if (filtroTamano == 0) {
            tipo = "TODAS";
        } else if (filtroTamano == 1) {
            tipo = "PEQUEÑAS (< 12 m2)";
        } else if (filtroTamano == 2) {
            tipo = "GRANDES (>= 12 m2)";
        }

        System.out.println("\n=== Plazas de garaje " + tipo + " ===");
        boolean encontrada = false;

        for (int sotano = 0; sotano < PLANTAS_GARAJE; sotano++) {
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje p = garaje[sotano][plaza];
                if (p != null && p.cumpleTamano(filtroTamano) && p.estaDisponible()) {
                    System.out.println("Sótano " + (sotano + 1) + ", Plaza " + plaza + ": " + p);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron plazas que cumplan el criterio.");
        }
    }

    // 7.3 Búsqueda combinada de plazas de garaje
    public void buscarPlazasGaraje(double metrosMin, double metrosMax, double precioMin, double precioMax, int filtroTamano) {
        System.out.println("\n=== Búsqueda combinada de plazas de garaje ===");
        System.out.println("Criterios:");
        System.out.println("  - Superficie: " + metrosMin + " - " + metrosMax + " m2");
        System.out.println("  - Precio: " + precioMin + " - " + precioMax + " €");
        System.out.println("  - Tamaño: " + filtroTamano);
        System.out.println("Resultados:");

        boolean encontrada = false;

        for (int sotano = 0; sotano < PLANTAS_GARAJE; sotano++) {
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje p = garaje[sotano][plaza];
                if (p != null &&
                        p.cumpleSuperficie(metrosMin, metrosMax) &&
                        p.cumplePrecio(precioMin, precioMax) &&
                        p.cumpleTamano(filtroTamano) &&
                        p.estaDisponible()) {
                    System.out.println("Sótano " + (sotano + 1) + ", Plaza " + plaza + ": " + p);
                    encontrada = true;
                }
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron plazas que cumplan todos los criterios.");
        }
    }

    // 7.4 Búsqueda de trasteros por superficie
    public void buscarTrasterosPorSuperficie(double metrosMin, double metrosMax) {
        System.out.println("\n=== Trasteros con superficie entre " + metrosMin + " y " + metrosMax + " m2 ===");
        boolean encontrada = false;

        for (int i = 0; i < numTrasteros; i++) {
            Trastero t = trasteros[i];
            if (t != null && t.cumpleSuperficie(metrosMin, metrosMax) && t.estaDisponible()) {
                System.out.println("Trastero " + i + ": " + t);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron trasteros que cumplan el criterio.");
        }
    }

    // 7.4 Búsqueda de trasteros por precio
    public void buscarTrasterosPorPrecio(double precioMin, double precioMax) {
        System.out.println("\n=== Trasteros con precio entre " + precioMin + " y " + precioMax + " € ===");
        boolean encontrada = false;

        for (int i = 0; i < numTrasteros; i++) {
            Trastero t = trasteros[i];
            if (t != null && t.cumplePrecio(precioMin, precioMax) && t.estaDisponible()) {
                System.out.println("Trastero " + i + ": " + t);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron trasteros que cumplan el criterio.");
        }
    }

    // 7.4 Búsqueda de trasteros por tamaño (0: todos, 1: pequeños, 2: grandes)
    public void buscarTrasterosPorTamano(int filtroTamano) {
        String tipo = "";
        if (filtroTamano == 0) {
            tipo = "TODAS";
        } else if (filtroTamano == 1) {
            tipo = "PEQUEÑAS (< 7 m2)";
        } else if (filtroTamano == 2) {
            tipo = "GRANDES (>= 7 m2)";
        }

        System.out.println("\n=== Trasteros " + tipo + " ===");
        boolean encontrada = false;

        for (int i = 0; i < numTrasteros; i++) {
            Trastero t = trasteros[i];
            if (t != null && t.cumpleTamano(filtroTamano) && t.estaDisponible()) {
                System.out.println("Trastero " + i + ": " + t);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron trasteros que cumplan el criterio.");
        }
    }

    // 7.4 Búsqueda combinada de trasteros
    public void buscarTrasteros(double metrosMin, double metrosMax, double precioMin, double precioMax, int filtroTamano) {
        System.out.println("\n=== Búsqueda combinada de trasteros ===");
        System.out.println("Criterios:");
        System.out.println("  - Superficie: " + metrosMin + " - " + metrosMax + " m2");
        System.out.println("  - Precio: " + precioMin + " - " + precioMax + " €");
        System.out.println("  - Tamaño: " + filtroTamano);
        System.out.println("Resultados:");

        boolean encontrada = false;

        for (int i = 0; i < numTrasteros; i++) {
            Trastero t = trasteros[i];
            if (t != null &&
                    t.cumpleSuperficie(metrosMin, metrosMax) &&
                    t.cumplePrecio(precioMin, precioMax) &&
                    t.cumpleTamano(filtroTamano) &&
                    t.estaDisponible()) {
                System.out.println("Trastero " + i + ": " + t);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron trasteros que cumplan todos los criterios.");
        }
    }


    // Union viviendas
    public boolean puedenUnirseViviendas(int planta, int puerta1, int puerta2) {
        if (puerta1 - puerta2 == 1 || puerta2 - puerta1 == 1) {
            if (getVivienda(planta, puerta1) != null && getVivienda(planta, puerta2) != null) {
                return getVivienda(planta, puerta1).estaDisponible() &&
                        getVivienda(planta, puerta2).estaDisponible();
            }

        }
        return false;
    }

    public boolean unirViviendas(int planta, int puerta1, int puerta2, String dni, Vivienda.Calidad calidad) {
        if (puerta1 > puerta2) {
            int aux = puerta2; // Auxiliar para intercambiar puertas
            puerta2 = puerta1;
            puerta1 = aux;
        }
        if (puedenUnirseViviendas(planta, puerta1, puerta2)) {
            // Guardamos en variables la suma del precio metros cuadrados y habitaciones
            double precioNuevo = getVivienda(planta, puerta1).getPrecio() + getVivienda(planta, puerta2).getPrecio();
            double metrosNuevo = getVivienda(planta, puerta1).getMetrosCuadrados()
                    + getVivienda(planta, puerta2).getMetrosCuadrados();
            int habNuevo = getVivienda(planta, puerta1).getHabitaciones()
                    + getVivienda(planta, puerta2).getHabitaciones();

            // Nueva vivienda con características nuevas y venta al comprador
            Vivienda nueva = new Vivienda(precioNuevo, metrosNuevo, habNuevo);
            if(nueva.vender(dni, calidad)){
                setVivienda(planta, puerta1, nueva);
            }

            //20. Desplazar todas las viviendas a la izquierda a partir de puerta2
            // Guardo la vivienda a partir de puerta 2
            for (int i = puerta2; i < viviendas[planta].length - 1; i++) {
                viviendas[planta][i] = viviendas[planta][i + 1]; //Guardo en la posición actual la vivienda siguiente
            }
            viviendas[planta][viviendas[planta].length - 1] = null; // Null en la última posición después de desplazar
            return true;
        } else return false;
    }

    // Union trasteros
    public boolean puedenUnirseTrasteros(int trastero1, int trastero2) {
        if (trastero1 - trastero2 == 1 || trastero2 - trastero1 == 1) {
            if (getTrastero(trastero1) != null && getTrastero(trastero2) != null) {
                return getTrastero(trastero1).estaDisponible() && getTrastero(trastero2).estaDisponible();
            }

        }
        return false;
    }

    public boolean unirTrasteros(int trastero1, int trastero2, String dniComprador) {
        if (trastero1 > trastero2) {
            int aux = trastero2; // Auxiliar para intercambiar trasteros
            trastero2 = trastero1;
            trastero1 = aux;
        }
        if (puedenUnirseTrasteros(trastero1, trastero2)) {
            double precioNuevo = getTrastero(trastero1).getPrecio() + getTrastero(trastero2).getPrecio();
            double metrosNuevo = getTrastero(trastero1).getMetrosCuadrados() + getTrastero(trastero2).getMetrosCuadrados();

            Trastero nuevo = new Trastero(precioNuevo, metrosNuevo);
            if(nuevo.vender(dniComprador)){
                trasteros[trastero1] = nuevo;
            }

            for (int i = trastero2; i < trasteros.length - 1; i++) {
                trasteros[i] = trasteros[i + 1]; //Guardo en la posición actual la vivienda siguiente
            }
            trasteros[trasteros.length - 1] = null; // Null en la última posición después de desplazar
            return true;
        } else return false;
    }


    // Métodos de Conteo
    public int contarViviendasPorDni(String dni) {
        int contar = 0;
        if (dni == null || dni.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < viviendas.length; i++) {
                for (int j = 0; j < viviendas[0].length; j++) {
                    if (viviendas[i][j] != null &&
                            viviendas[i][j].getEstado() == Vivienda.Estado.VENDIDO &&
                            dni.equalsIgnoreCase(viviendas[i][j].getDniComprador())) {
                        contar++;
                    }
                }
            }
        }
        return contar;
    }

    public int contarPlazasPorDni(String dni) {
        int contar = 0;
        if (dni == null || dni.isEmpty()) {
            return 0;
        } else {
            for (int i = 0; i < garaje.length; i++) {
                for (int j = 0; j < garaje[0].length; j++) {
                    if (garaje[i][j] != null &&
                            garaje[i][j].getEstado() == PlazaGaraje.Estado.VENDIDO &&
                            dni.equalsIgnoreCase(garaje[i][j].getDniComprador())) {
                        contar++;
                    }
                }
            }
        }
        return contar;

    }

    public int contarTrasterosPorDni(String dni) {
        int contar = 0;

        if (dni == null || dni.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < trasteros.length; i++) {
            if (trasteros[i] != null &&
                    trasteros[i].getEstado() == Trastero.Estado.VENDIDO &&
                    dni.equalsIgnoreCase(trasteros[i].getDniComprador())) {
                contar++;
            }
        }

        return contar;
    }

    // Métodos de Listado
    public String listarViviendasPorDni(String dni) {
        StringBuilder detalles = new StringBuilder();
        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        } else {
            for (int i = 0; i < viviendas.length; i++) {
                for (int j = 0; j < viviendas[0].length; j++) {
                    if (viviendas[i][j] != null) {
                        if (viviendas[i][j].getEstado() == Vivienda.Estado.VENDIDO && dni.equalsIgnoreCase(viviendas[i][j].getDniComprador())) {
                            detalles.append(viviendas[i][j].getDetalles());
                        }
                    }
                }
            }
            if (detalles.isEmpty()) {
                detalles = new StringBuilder("No hay detalles");
            }
            return detalles.toString();
        }
    }

    public String listarPlazasPorDni(String dni) {
        StringBuilder detalles = new StringBuilder();
        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        }
        for (int i = 0; i < garaje.length; i++) {
            for (int j = 0; j < garaje[0].length; j++) {
                if (garaje[i][j] != null &&
                        garaje[i][j].getEstado() == PlazaGaraje.Estado.VENDIDO &&
                        dni.equalsIgnoreCase(garaje[i][j].getDniComprador())) {
                    detalles.append(garaje[i][j].getDetalles());
                }
            }
        }
        if (detalles.isEmpty()) {
            detalles = new StringBuilder("No hay detalles");
        }
        return detalles.toString();
    }

    public String listarTrasterosPorDni(String dni) {
        StringBuilder detalles = new StringBuilder();

        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        }
        for (int i = 0; i < trasteros.length; i++) {
            if (trasteros[i] != null &&
                    trasteros[i].getEstado() == Trastero.Estado.VENDIDO &&
                    dni.equalsIgnoreCase(trasteros[i].getDniComprador())) {
                detalles.append(trasteros[i].getDetalles());
            }
        }

        if (detalles.isEmpty()) {
            detalles = new StringBuilder("No hay detalles");
        }

        return detalles.toString();

    }
}