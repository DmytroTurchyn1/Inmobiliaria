import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inmobiliaria");
    }


    public void menuVenderReservar() {
        Scanner sc1 = new Scanner(System.in);

        System.out.println("Pulsar (1) para vender \n" +
                "Pulsar (2) para reservar");

        int opc = sc1.nextInt();
        if (opc == 1) {

        } else if (opc == 2) {

        }
    }

 /*
        Submenús

        1º - menuVenderReservar() -> vende vivienda y reserva
        2º - venderPlazaGaraje() ->  vende plaza garaje
        3º - venderTrasteros() -> vende trastero
        4º - unirViviendas() -> une dos viviendas que seleccione el usu
        5º - unirTrasteros() -> une dos trasteros que seleccione el usu
        6º - Menús búsqueda -> buscarViviendas() [viv por sup, precio..], buscarTrasteros()[por sup, precio..), plazas..
        7º - consultarPropiedadesPorDni
        8º - Ver estadisticas -> mostrarEstadisticasGenerales...



        */
}
