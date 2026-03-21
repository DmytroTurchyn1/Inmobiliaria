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

        for(int planta = numPlantas - 1; planta >= 0; planta--){
            System.out.println("Planta"+planta);

            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null){
                    switch (vivienda.estado) {
                        case LIBRE:
                            System.out.print("[L]");
                            break;
                        case RESERVADO:
                            System.out.print("[R]");
                            break;
                        case VENDIDO:
                            System.out.print("[V]");
                            break;
                    };

                }


            }

            System.out.println();

        }

    }

    public void mostrarEdificio(){
        mostrarEstado();

        System.out.println("[GARAJE]");
        for (int i = 0; i < plazasPorPlantaGaraje; i++){
            System.out.printf("   P"+(i+1));
        }


        for (int planta = 0; planta < 2; planta++) {

            System.out.println("Sotano -"+(planta+1));
            for(int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++){
                PlazaGaraje plazaGaraje = garaje[planta][plaza];
                if(plazaGaraje != null){
                    switch (plazaGaraje.estado) {
                        case LIBRE:
                            System.out.print("[L]");
                            break;
                        case VENDIDO:
                            System.out.print("[V]");
                            break;
                    };
                }

            }
            System.out.println();
        }

        System.out.println("[TRASTERO]");
        for (int i = 0; i < numTrasteros; i++){
            System.out.printf("   T"+(i+1));
        }

        for(int plaza = 0; plaza < numTrasteros; plaza++){
            Trastero trastero = trasteros[plaza];
            if(trastero != null){
                switch (trastero.estado) {
                    case LIBRE:
                        System.out.print("[L]");
                        break;
                    case VENDIDO:
                        System.out.print("[V]");
                        break;
                }
            }


        }
        System.out.println();

    }

    public int getTotalViviendas(){
        int contadorTotViviendas = 0;

        for(int planta = numPlantas - 1; planta >= 0; planta--){
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null){
                    contadorTotViviendas = contadorTotViviendas +1;

                }

            }
        }

        return contadorTotViviendas;
    }

    public int contarViviendasLibres(){
        int contadorVivLibres = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--){
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null && vivienda.estado == Vivienda.Estado.LIBRE ){
                    contadorVivLibres = contadorVivLibres + 1;
                }
            }
        }

        return contadorVivLibres;
    }

    public int contarViviendasReservadas(){
        int contadorVivReservadas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--){
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null && vivienda.estado == Vivienda.Estado.RESERVADO){
                    contadorVivReservadas = contadorVivReservadas + 1;
                }
            }
        }

        return contadorVivReservadas;
    }

    public int contarViviendasVendidas(){
        int contadorVivVendidas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--){
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null && vivienda.estado == Vivienda.Estado.VENDIDO){
                    contadorVivVendidas = contadorVivVendidas + 1;
                }
            }
        }

        return contadorVivVendidas;
    }

    public int getTotalPlazasGaraje(){
        int totalPlazasGaraje = 0;

        for(int planta = 0; planta < 2; planta++){
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++){
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if(plazaGaraje != null){
                    totalPlazasGaraje = totalPlazasGaraje + 1;
                }
            }
        }

        return  totalPlazasGaraje;
    }

    public int contarPlazasGarajeVendidas(){
        int plazasGarajeVendidas = 0;

        for(int planta = 0; planta < 2; planta++){
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++){
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if(plazaGaraje != null && plazaGaraje.estado == PlazaGaraje.Estado.VENDIDO){
                    plazasGarajeVendidas = plazasGarajeVendidas + 1;
                }
            }
        }

        return  plazasGarajeVendidas;
    }

    public int contarPlazasGarajeLibres(){
        int plazasGarajeLibres = 0;

        for(int planta = 0; planta < 2; planta++){
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++){
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if(plazaGaraje != null && plazaGaraje.estado == PlazaGaraje.Estado.LIBRE){
                    plazasGarajeLibres = plazasGarajeLibres + 1;
                }
            }
        }

        return plazasGarajeLibres;
    }


    public int getTotalTrasteros(){
        int numTotalTrasteros = 0;

        for(int puerta = 0; puerta < numTrasteros; puerta++){
            Trastero trastero = trasteros[puerta];
            if(trastero != null){
                numTotalTrasteros = numTotalTrasteros + 1;
            }
        }

        return numTotalTrasteros;
    }

    public int totalTrasterosVendidos(){
        int trasterosVendidos = 0;

        for(int puerta = 0;  puerta < numTrasteros ; puerta++){
            Trastero trastero = trasteros[puerta];
            if(trastero != null && trastero.estado == Trastero.Estado.VENDIDO ){
                trasterosVendidos = trasterosVendidos + 1;
            }
        }

        return trasterosVendidos;
    }

    public int totalTrasterosLibres(){
        int trasterosLibres = 0;

        for(int puerta = 0;  puerta < numTrasteros ; puerta++){
            Trastero trastero = trasteros[puerta];
            if(trastero != null && trastero.estado == Trastero.Estado.LIBRE ){
                trasterosLibres = trasterosLibres + 1;
            }
        }

        return trasterosLibres;
    }


    //CALCULOS

    public double calculoIngresoPotencialesViviendas(){
        double ingresoPotencial = 0;

        for(int planta = numPlantas - 1; planta >= 0; planta--){
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null){
                    ingresoPotencial = ingresoPotencial + vivienda.getPrecio();
                }

            }
        }

        return ingresoPotencial;

    }

    public double calculoIngresosVivVendidas(){
        double ingresoVivVendidas = 0;

        for (int planta = numPlantas - 1; planta >= 0; planta--){
            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++){
                Vivienda vivienda = viviendas[planta][puerta];

                if(vivienda != null && vivienda.estado == Vivienda.Estado.VENDIDO){
                    ingresoVivVendidas = ingresoVivVendidas + vivienda.getPrecio();
                }
            }
        }

        return ingresoVivVendidas;
    }

    public double calculoIngresoPotencialPlazaGaraje(){
        double ingresoPotencialPlazGaraje = 0;

        for(int planta = 0; planta < 2; planta++){
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++){
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if(plazaGaraje != null){
                    ingresoPotencialPlazGaraje = ingresoPotencialPlazGaraje + plazaGaraje.getPrecio();
                }
            }
        }

        return ingresoPotencialPlazGaraje;
    }

    public double calculoIngresosVendidosPlazGaraj(){
        double ingresoVendidosPLazas = 0;

        for(int planta = 0; planta < 2; planta++){
            for (int plazas = 0; plazas < plazasPorPlantaGaraje; plazas++){
                PlazaGaraje plazaGaraje = garaje[planta][plazas];

                if(plazaGaraje != null && plazaGaraje.estado == PlazaGaraje.Estado.VENDIDO){
                    ingresoVendidosPLazas = ingresoVendidosPLazas + plazaGaraje.getPrecio();
                }
            }
        }

        return ingresoVendidosPLazas;
    }

    public double calculoIngresoPotencialesTrasteros(){
        double ingresoPotencialesTrasteros = 0;

        for(int puerta = 0; puerta < numTrasteros; puerta++){
            Trastero trastero = trasteros[puerta];
            if(trastero != null){
                ingresoPotencialesTrasteros = ingresoPotencialesTrasteros + trastero.getPrecio();
            }
        }

        return ingresoPotencialesTrasteros;
    }

    public double calculoTrasterosVendidos(){
        double ingresoTrasterosVendidos = 0;

        for(int puerta = 0;  puerta < numTrasteros ; puerta++){
            Trastero trastero = trasteros[puerta];
            if(trastero != null && trastero.estado == Trastero.Estado.VENDIDO ){
                ingresoTrasterosVendidos = ingresoTrasterosVendidos + trastero.getPrecio();
            }
        }

        return ingresoTrasterosVendidos;
    }

}

