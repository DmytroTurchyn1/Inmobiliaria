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

}
