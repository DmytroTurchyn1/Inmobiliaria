import modelo.Edificio;
import modelo.Promotora;
import modelo.Vivienda;

import java.util.Scanner;

class Main {

    private static Scanner scanner;
    private static Promotora promotora;


    static void main(String[] args) {
        scanner = new Scanner(System.in);
        inicializar();
        menuPrincipal();
        scanner.close();
    }

    //inicializo con los valores que quiero
    private static void inicializar() {
        promotora = new Promotora("Inmobiliaria SA");

        promotora.agregarEdificio(new Edificio("Edificio Azul",   5, 4, 8, 10));
        promotora.agregarEdificio(new Edificio("Edificio Verde",  4, 3, 6,  8));
        promotora.agregarEdificio(new Edificio("Edificio Rojo",   6, 5, 10, 12));

        System.out.println("=== Bienvenido a " + promotora.getNombre() + " ===\n");
    }

    //f metodo de auxiliares
    public static String leerString() {
        return scanner.nextLine().trim();
    }

    public static int leerEntero() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida, introduce un número entero: ");
            }
        }
    }

    public static double leerDouble() {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida, introduce un número decimal: ");
            }
        }
    }

    //f menu principal --- diseño hecho con ia (Logica por mi(IVAN))
    private static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        MENÚ PRINCIPAL        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Gestionar edificios       ║");
            System.out.println("║ 2. Vender / Reservar         ║");
            System.out.println("║ 3. Vender plaza de garaje    ║");
            System.out.println("║ 4. Vender trastero           ║");
            System.out.println("║ 5. Unir viviendas            ║");
            System.out.println("║ 6. Unir trasteros            ║");
            System.out.println("║ 7. Búsquedas                 ║");
            System.out.println("║ 8. Propiedades por DNI       ║");
            System.out.println("║ 9. Estadísticas generales    ║");
            System.out.println("║ 0. Salir                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Selecciona una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> gestionarEdificios();       // FASE G
                case 2 -> menuVenderReservar();
                case 3 -> venderPlazaGaraje();
                case 4 -> venderTrastero();
                case 5 -> unirViviendas();
                case 6 -> unirTrasteros();
                case 7 -> menuBusquedas();
                case 8 -> listarPropiedadesPorDni();
                case 9 -> mostrarEstadisticasGenerales();
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    //Parte F
    private static void gestionarEdificios() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║      GESTIÓN DE EDIFICIOS    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Listar todos los edificios║");
            System.out.println("║ 2. Ver estado de un edificio ║");
            System.out.println("║ 3. Ver matriz de un edificio ║");
            System.out.println("║ 0. Volver al menú principal  ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Selecciona una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> listarEdificios();
                case 2 -> verEstadoEdificio();
                case 3 -> verMatrizEdificio();
                case 0 -> System.out.println("Volviendo al menú principal");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    private static void listarEdificios() {
        System.out.println("\n─ EDIFICIOS DISPONIBLES ─");
        promotora.listarEdificios();
    }

    //ver edificio estasdod
    private static void verEstadoEdificio() {
        System.out.println("\n──── VER ESTADO DE EDIFICIO ────");
        promotora.listarEdificios();
        System.out.print("Seleccione el número de edificio: ");
        int indice = leerEntero();

        Edificio edificio = promotora.getEdificio(indice);
        if (edificio == null) {
            System.out.println("ERROR: Edificio no encontrado.");
            return;
        }
        edificio.mostrarEstado();
    }


    private static void verMatrizEdificio() {
        System.out.println("\n──── VER MATRIZ DE EDIFICIO ────");
        promotora.listarEdificios();
        System.out.print("Seleccione el número de edificio: ");
        int indice = leerEntero();

        Edificio edificio = promotora.getEdificio(indice);
        if (edificio == null) {
            System.out.println("ERROR: Edificio no encontrado.");
            return;
        }
        edificio.mostrarMatrizEdificio();
    }


    //menus de v ender
    public static void menuVenderReservar() {
        System.out.println("----EDIFICIOS----");
        promotora.listarEdificios();

        System.out.println("Pulsar (1) para vender \nPulsar (2) para reservar");
        int eleccion = leerEntero();

        if (eleccion == 1) {
            System.out.println("\n----Menú de Ventas----\n");
            System.out.print("Seleccione un edificio: ");   int edificio      = leerEntero();
            System.out.print("\nSeleccione una planta: ");  int planta        = leerEntero();
            System.out.print("\nSeleccione una puerta: ");  int puerta        = leerEntero();
            System.out.print("Inserte su DNI: ");           String dniComp    = leerString();

            System.out.print("Seleccione calidad (ESTANDAR-1, PLUS-2, DE_LUXE-3): ");
            int opcionCalidad = leerEntero();
            while (opcionCalidad < 1 || opcionCalidad > 3) {
                System.out.print("Introduce una calidad correcta: ");
                opcionCalidad = leerEntero();
            }
            Vivienda.Calidad calidad = opcionToCalidad(opcionCalidad);
            promotora.venderVivienda(edificio, planta, puerta, dniComp, calidad);

        } else if (eleccion == 2) {
            System.out.println("\n----Menú de Reservas----\n");
            System.out.print("Seleccione un edificio: ");   int edificio   = leerEntero();
            System.out.print("\nSeleccione una planta: ");  int planta     = leerEntero();
            System.out.print("\nSeleccione una puerta: ");  int puerta     = leerEntero();
            System.out.print("Inserte su DNI: ");           String dni     = leerString();

            System.out.print("Seleccione calidad (ESTANDAR-1, PLUS-2, DE_LUXE-3): ");
            int opcionCalidad = leerEntero();
            while (opcionCalidad < 1 || opcionCalidad > 3) {
                System.out.print("Introduce una calidad correcta: ");
                opcionCalidad = leerEntero();
            }
            Vivienda.Calidad calidad = opcionToCalidad(opcionCalidad);
            promotora.reservarVivienda(edificio, planta, puerta, dni, calidad);
        }
    }

    public static void venderPlazaGaraje() {
        System.out.print("Seleccione un edificio: "); int edificio = leerEntero();
        System.out.print("\nSeleccione el sótano: ");  int sotano   = leerEntero();
        System.out.print("\nSeleccione la plaza: ");   int plaza    = leerEntero();
        System.out.print("Inserte su DNI: ");          String dni   = leerString();
        promotora.venderPlazaGaraje(edificio, sotano, plaza, dni);
    }

    public static void venderTrastero() {
        System.out.print("Seleccione un edificio: "); int edificio = leerEntero();
        System.out.print("\nSeleccione el trastero: ");int indice   = leerEntero();
        System.out.print("\nInserte su DNI: ");        String dni   = leerString();
        promotora.venderTrastero(edificio, indice, dni);
    }

    public static void unirViviendas() {
        System.out.println("----EDIFICIOS----");
        promotora.listarEdificios();

        System.out.print("\nSeleccione un edificio: "); int edificio = leerEntero();
        System.out.print("\nSeleccione una planta: ");  int planta   = leerEntero();
        System.out.print("\nPrimera puerta contigua: "); int puerta1 = leerEntero();
        System.out.print("\nSegunda puerta contigua: "); int puerta2 = leerEntero();
        System.out.print("\nInserte su DNI: ");          String dniComp = leerString();

        System.out.print("\nSeleccione calidad (ESTANDAR-1, PLUS-2, DE_LUXE-3): ");
        int opcionCalidad = leerEntero();
        while (opcionCalidad < 1 || opcionCalidad > 3) {
            System.out.print("Introduce una calidad correcta: ");
            opcionCalidad = leerEntero();
        }
        Vivienda.Calidad calidad = opcionToCalidad(opcionCalidad);

        Edificio edificio1 = promotora.getEdificio(edificio);
        while (edificio1 == null) {
            System.out.print("\nSeleccione un edificio correcto: ");
            edificio1 = promotora.getEdificio(leerEntero());
        }
        edificio1.unirViviendas(planta, puerta1, puerta2, dniComp, calidad);
    }

    public static void unirTrasteros() {
        System.out.print("\nSeleccione un edificio: ");      int edificio  = leerEntero();
        System.out.print("\nSeleccione el primer trastero: ");int trastero1 = leerEntero();
        System.out.print("\nSeleccione el segundo trastero: ");int trastero2 = leerEntero();
        System.out.print("\nInserte su DNI: ");               String dni    = leerString();

        Edificio edificio1 = promotora.getEdificio(edificio);
        while (edificio1 == null) {
            System.out.print("\nSeleccione un edificio correcto: ");
            edificio1 = promotora.getEdificio(leerEntero());
        }
        edificio1.unirTrasteros(trastero1, trastero2, dni);
    }

    //menu bsquedas
    private static void menuBusquedas() {
        System.out.println("\n1-Viviendas por superficie\n2-Viviendas por precio\n3-Viviendas por habitaciones");
        System.out.println("4-Búsqueda combinada viviendas");
        System.out.println("5-Plazas garaje por superficie\n6-Plazas garaje por precio\n7-Plazas garaje por tamaño");
        System.out.println("8-Búsqueda combinada garaje");
        System.out.println("9-Trasteros por superficie\n10-Trasteros por precio\n11-Trasteros por tamaño");
        System.out.println("12-Búsqueda combinada trasteros");
        System.out.print("\nOpción: ");
        int op = leerEntero();
        switch (op) {
            case 1  -> buscarViviendasPorSuperficie();
            case 2  -> buscarViviendasPorPrecio();
            case 3  -> buscarViviendasPorHabitaciones();
            case 4  -> buscarViviendas();
            case 5  -> buscarPlazasGarajePorSuperficie();
            case 6  -> buscarPlazasGarajePorPrecio();
            case 7  -> buscarPlazasGarajePorTamano();
            case 8  -> buscarPlazasGaraje();
            case 9  -> buscarTrasterosPorSuperficie();
            case 10 -> buscarTrasterosPorPrecio();
            case 11 -> buscarTrasterosPorTamano();
            case 12 -> buscarTrasteros();
            default -> System.out.println("Opción no válida.");
        }
    }

    // redireccion de busquedas
    public static void buscarViviendasPorSuperficie() {
        System.out.print("\nMetros mínimos: "); double metrosMin = leerDouble();
        System.out.print("\nMetros máximos: "); double metrosMax = leerDouble();
        promotora.buscarViviendasPorSuperficie(metrosMin, metrosMax);
    }
    public static void buscarViviendasPorPrecio() {
        System.out.print("\nPrecio mínimo: "); double precioMin = leerDouble();
        System.out.print("\nPrecio máximo: "); double precioMax = leerDouble();
        promotora.buscarViviendasPorPrecio(precioMin, precioMax);
    }
    public static void buscarViviendasPorHabitaciones() {
        System.out.print("\nHabitaciones mínimas: "); int habMin = leerEntero();
        System.out.print("\nHabitaciones máximas: "); int habMax = leerEntero();
        promotora.buscarViviendasPorHabitaciones(habMin, habMax);
    }
    public static void buscarViviendas() {
        System.out.print("\nMetros mínimos: ");       double metrosMin = leerDouble();
        System.out.print("\nMetros máximos: ");       double metrosMax = leerDouble();
        System.out.print("\nPrecio mínimo: ");        double precioMin = leerDouble();
        System.out.print("\nPrecio máximo: ");        double precioMax = leerDouble();
        System.out.print("\nHabitaciones mínimas: "); int habMin       = leerEntero();
        System.out.print("\nHabitaciones máximas: "); int habMax       = leerEntero();
        promotora.buscarViviendas(metrosMin, metrosMax, precioMin, precioMax, habMin, habMax);
    }
    public static void buscarPlazasGarajePorSuperficie() {
        System.out.print("\nMetros mínimos: "); double metrosMin = leerDouble();
        System.out.print("\nMetros máximos: "); double metrosMax = leerDouble();
        promotora.buscarPlazasGarajePorSuperficie(metrosMin, metrosMax);
    }
    public static void buscarPlazasGarajePorPrecio() {
        System.out.print("\nPrecio mínimo: "); double precioMin = leerDouble();
        System.out.print("\nPrecio máximo: "); double precioMax = leerDouble();
        promotora.buscarPlazasGarajePorPrecio(precioMin, precioMax);
    }
    public static void buscarPlazasGarajePorTamano() {
        System.out.print("\nTamaño (0=todas, 1=pequeñas, 2=grandes): ");
        int filtroTamano = leerEntero();
        promotora.buscarPlazasGarajePorTamano(filtroTamano);
    }
    public static void buscarPlazasGaraje() {
        System.out.print("\nMetros mínimos: "); double metrosMin = leerDouble();
        System.out.print("\nMetros máximos: "); double metrosMax = leerDouble();
        System.out.print("\nPrecio mínimo: ");  double precioMin = leerDouble();
        System.out.print("\nPrecio máximo: ");  double precioMax = leerDouble();
        System.out.print("\nTamaño (0=todas, 1=pequeñas, 2=grandes): ");
        int filtroTamano = leerEntero();
        promotora.buscarPlazasGaraje(metrosMin, metrosMax, precioMin, precioMax, filtroTamano);
    }
    public static void buscarTrasterosPorSuperficie() {
        System.out.print("\nMetros mínimos: "); double metrosMin = leerDouble();
        System.out.print("\nMetros máximos: "); double metrosMax = leerDouble();
        promotora.buscarTrasterosPorSuperficie(metrosMin, metrosMax);
    }
    public static void buscarTrasterosPorPrecio() {
        System.out.print("\nPrecio mínimo: "); double precioMin = leerDouble();
        System.out.print("\nPrecio máximo: "); double precioMax = leerDouble();
        promotora.buscarTrasterosPorPrecio(precioMin, precioMax);
    }
    public static void buscarTrasterosPorTamano() {
        System.out.print("\nTamaño (0=todos, 1=pequeños, 2=grandes): ");
        int filtroTamano = leerEntero();
        promotora.buscarTrasterosPorTamano(filtroTamano);
    }
    public static void buscarTrasteros() {
        System.out.print("\nMetros mínimos: "); double metrosMin = leerDouble();
        System.out.print("\nMetros máximos: "); double metrosMax = leerDouble();
        System.out.print("\nPrecio mínimo: ");  double precioMin = leerDouble();
        System.out.print("\nPrecio máximo: ");  double precioMax = leerDouble();
        System.out.print("\nTamaño (0=todos, 1=pequeños, 2=grandes): ");
        int filtroTamano = leerEntero();
        promotora.buscarTrasteros(metrosMin, metrosMax, precioMin, precioMax, filtroTamano);
    }
    public static void listarPropiedadesPorDni() {
        System.out.print("Inserte su DNI: ");
        String dni = leerString();
        promotora.listarPropiedadesPorDni(dni);
    }
    public static void mostrarEstadisticasGenerales() {
        promotora.mostrarEstadisticasGenerales();
    }

    private static Vivienda.Calidad opcionToCalidad(int opcion) {
        return switch (opcion) {
            case 1  -> Vivienda.Calidad.ESTANDAR;
            case 2  -> Vivienda.Calidad.PLUS;
            default -> Vivienda.Calidad.DE_LUXE;
        };
    }
}