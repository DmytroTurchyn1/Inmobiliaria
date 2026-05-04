import modelo.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inmobiliaria");
    }

    public String leerString(){
        return scanner.nextLine();
    }
    public void menuVenderReservar() {
        System.out.println("----EDIFICIOS----");
        promotora.listarEdificios();

        System.out.println("Pulsar (1) para vender \n" +
                "Pulsar (2) para reservar");
        int eleccion = leerEntero();

        // Proceso para vender vivienda de un edificio concreto
        if (eleccion == 1) {
            System.out.println("\n----Menu de Ventas----\n");
            System.out.println("Seleccione un edificio: ");
            int edificio = leerEntero();
            System.out.println("\nSeleccione una planta: ");
            int planta = leerEntero();
            System.out.println("\nSeleccione una puerta: ");
            int puerta = leerEntero();

            System.out.println("Inserte su DNI porfavor: ");
            String dniComp = leerString();

            System.out.println("Seleccione una calidad (ESTANDAR-1, PLUS-2, DE_LUXE-3: ");
            int opcionCalidad = leerEntero();

            Vivienda.Calidad calidad = null;
            if (opcionCalidad == 1)calidad = Vivienda.Calidad.ESTANDAR;
            if (opcionCalidad == 2) calidad = Vivienda.Calidad.PLUS;
            if (opcionCalidad == 3) calidad = Vivienda.Calidad.DE_LUXE;

            promotora.venderVivienda(edificio,planta,puerta,dniComp,calidad);

        } else if (eleccion == 2) {
            System.out.println("\n----Menu de Reservas----\n");

            System.out.println("Seleccione un edificio: ");
            int edificio = leerEntero();
            System.out.println("\nSeleccione una planta: ");
            int planta = leerEntero();
            System.out.println("\nSeleccione una puerta: ");
            int puerta = leerEntero();

            System.out.println("Inserte su DNI porfavor: ");
            String dni = leerString();

            System.out.println("Seleccione una calidad (ESTANDAR-1, PLUS-2, DE_LUXE-3: ");
            int opcionCalidad = leerEntero();

            Vivienda.Calidad calidad = null;
            if (opcionCalidad == 1)calidad = Vivienda.Calidad.ESTANDAR;
            if (opcionCalidad == 2) calidad = Vivienda.Calidad.PLUS;
            if (opcionCalidad == 3) calidad = Vivienda.Calidad.DE_LUXE;

            promotora.reservarVivienda(edificio,planta,puerta,dni,calidad);
        }
    }
    public void venderPlazaGaraje(){
        System.out.println("Seleccione un edificio: ");
        int edificio = leerEntero();
        System.out.println("\nSeleccione el sotano: ");
        int sotano = leerEntero();
        System.out.println("\nSeleccione la plaza: ");
        int plaza = leerEntero();
        System.out.println("Inserte su DNI: ");
        String dni = leerString();

        promotora.venderPlazaGaraje(edificio, sotano, plaza, dni);
    }
    public void venderTrastero(){
        System.out.println("Seleccione un edificio: ");
        int edificio = leerEntero();
        System.out.println("\nSeleccione el trastero: ");
        int indice = leerEntero();
        System.out.println("\nInserte su DNI: ");
        String dni = leerString();

        promotora.venderTrastero(edificio, indice, dni);
    }
    public void unirViviendas(){
        System.out.println("----EDIFICIOS----");
        promotora.listarEdificios();

        System.out.println("\n Seleccione un edificio: ");
        int edificio = leerEntero();
        System.out.println("\nSeleccione una planta: ");
        int planta = leerEntero();
        System.out.println("\nSeleccione primera puerta contigua: ");
        int puerta1 = leerEntero();
        System.out.println("\nSeleccione segunda puerta contigua: ");
        int puerta2 = leerEntero();
        System.out.println("\nInserte su DNI porfavor: ");
        String dniComp = leerString();
        System.out.println("\nSeleccione calidad (ESTANDAR-1, PLUS-2, DE_LUXE-3: ");
        int opcionCalidad = leerEntero();

        Vivienda.Calidad calidad = null;
        if (opcionCalidad == 1) calidad = Vivienda.Calidad.ESTANDAR;
        if (opcionCalidad == 2) calidad = Vivienda.Calidad.PLUS;
        if (opcionCalidad == 3) calidad = Vivienda.Calidad.DE_LUXE;

        promotora.getEdificio(edificio).unirViviendas(planta,puerta1,puerta2,dniComp,calidad);
    }
    public void unirTrasteros(){
        System.out.println("\n Seleccione un edificio: ");
        int edificio = leerEntero();
        System.out.println("\n Seleccione el primer trastero: ");
        int trastero1 = leerEntero();
        System.out.println("\n Seleccione el segundo trastero: ");
        int trastero2 = leerEntero();
        System.out.println("\nInserte su DNI porfavor: ");
        String dni = leerString();


        promotora.getEdificio(edificio).unirTrasteros(trastero1, trastero2, dni);
    }
    public void buscarViviendasPorSuperficie(){
        System.out.println("\n Seleccione los metros minimos: ");
        double metrosMin = leerDouble();
        System.out.println("\n Seleccione los metros maximos: ");
        double metrosMax = leerDouble();

        promotora.buscarViviendasPorSuperficie(metrosMin, metrosMax);
    }
    public void buscarViviendasPorPrecio(){
        System.out.println("\n Seleccione el precio minimo: ");
        double precioMin = leerDouble();
        System.out.println("\n Seleccione el precio maximo: ");
        double precioMax = leerDouble();

        promotora.buscarViviendasPorPrecio(precioMin, precioMax);
    }
    public void buscarViviendasPorHabitaciones(){
        System.out.println("\n Seleccione habitaciones minimas: ");
        int habMin = leerEntero();
        System.out.println("\n Seleccione habitaciones maximas: ");
        int habMax = leerEntero();

        promotora.buscarViviendasPorHabitaciones(habMin, habMax);
    }
    public void buscarViviendas(){
        System.out.println("\n Seleccione los metros minimos: ");
        double metrosMin = leerDouble();
        System.out.println("\n Seleccione los metros maximos: ");
        double metrosMax = leerDouble();
        System.out.println("\n Seleccione el precio minimo: ");
        double precioMin = leerDouble();
        System.out.println("\n Seleccione el precio maximo: ");
        double precioMax = leerDouble();
        System.out.println("\n Seleccione habitaciones minimas: ");
        int habMin = leerEntero();
        System.out.println("\n Seleccione habitaciones maximas: ");
        int habMax = leerEntero();

        promotora.buscarViviendas(metrosMin, metrosMax,precioMin,precioMax,habMin,habMax);

    }
    public void buscarPlazasGarajePorSuperficie(){
        System.out.println("\n Seleccione los metros minimos: ");
        double metrosMin = leerDouble();
        System.out.println("\n Seleccione los metros maximos: ");
        double metrosMax = leerDouble();

        promotora.buscarPlazasGarajePorSuperficie(metrosMin, metrosMax);
    }
    public void buscarPlazasGarajePorPrecio(){
        System.out.println("\n Seleccione el precio minimo: ");
        double precioMin = leerDouble();
        System.out.println("\n Seleccione el precio maximo: ");
        double precioMax = leerDouble();

        promotora.buscarPlazasGarajePorPrecio(precioMin, precioMax);
    }
    public void buscarPlazasGarajePorTamano(){
        System.out.println("\n Seleccione el tamano: ");
        int filtroTamano = leerEntero();

        promotora.buscarPlazasGarajePorTamano(filtroTamano);
    }
    public void buscarPlazasGaraje(){
        System.out.println("\n Seleccione los metros minimos: ");
        double metrosMin = leerDouble();
        System.out.println("\n Seleccione los metros maximos: ");
        double metrosMax = leerDouble();
        System.out.println("\n Seleccione el precio minimo: ");
        double precioMin = leerDouble();
        System.out.println("\n Seleccione el precio maximo: ");
        double precioMax = leerDouble();

        promotora.buscarPlazasGaraje(metrosMin, metrosMax,precioMin,precioMax);
    }
    public void buscarTrasterosPorSuperficie(){
        System.out.println("\n Seleccione los metros minimos: ");
        double metrosMin = leerDouble();
        System.out.println("\n Seleccione los metros maximos: ");
        double metrosMax = leerDouble();

        promotora.buscarTrasterosPorSuperficie(metrosMin, metrosMax);
    }
    public void buscarTrasterosPorPrecio(){
        System.out.println("\n Seleccione el precio minimo: ");
        double precioMin = leerDouble();
        System.out.println("\n Seleccione el precio maximo: ");
        double precioMax = leerDouble();

        promotora.buscarTrasterosPorPrecio(precioMin, precioMax);
    }
    public void buscarTrasterosPorTamano(){
        System.out.println("\n Seleccione el tamano: ");
        int filtroTamano = leerEntero();

        promotora.buscarTrasterosPorTamano(filtroTamano);
    }
    public void buscarTrasteros(){
        System.out.println("\n Seleccione los metros minimos: ");
        double metrosMin = leerDouble();
        System.out.println("\n Seleccione los metros maximos: ");
        double metrosMax = leerDouble();
        System.out.println("\n Seleccione el precio minimo: ");
        double precioMin = leerDouble();
        System.out.println("\n Seleccione el precio maximo: ");
        double precioMax = leerDouble();

        promotora.buscarTrasteros(metrosMin, metrosMax,precioMin,precioMax);
    }
    public void listarPropiedadesPorDni(){
        System.out.println("Inserte su DNI: ");
        String dni = leerString();
        promotora.listarPropiedadesPorDni(dni);
    }
    public void mostrarEstadisticasGenerales(){
        promotora.mostrarEstadisticasGenerales();
    }

}
