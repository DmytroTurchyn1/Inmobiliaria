package modelo;

public class Edificio {

    public String nombre;
    public Vivienda[][] viviendas;
    public int numPlantas;
    public int viviendasPorPlanta;
    public PlazaGaraje[][] garaje;
    public int plazasPorPlantaGaraje;
    public Trastero[] trasteros;
    public int numTrasteros;


    public void mostrarEstado(){
        System.out.println("Edificio residencial "+nombre);
        for (int i = 0; i < viviendasPorPlanta; i++){
            System.out.printf("   P"+(i+1));

        }

        System.out.println(" ");

        for(int planta = numPlantas; planta >= 0; planta--){
            System.out.println("Planta"+planta);

            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null){
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
    }
}
