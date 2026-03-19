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
                    };

                }

            }
        }

    }

    public void mostrarEdificio(){
        mostrarEstado();

        System.out.println("[GARAJE]");
        for (int i = 0; i < plazasPorPlantaGaraje; i++){
            System.out.printf("   P"+(i+1));
        }


        for (int planta = 1; planta < 2; planta++) {

            System.out.println("Sotano "+(planta-2));
            for(int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++){
                PlazaGaraje plazaGaraje = garaje[planta][plaza];
                if(plazaGaraje != null){
                    switch (plazaGaraje.estado) {
                        case LIBRE:
                            System.out.println("[L]");
                            break;
                        case VENDIDO:
                            System.out.println("[V]");
                            break;
                    };
                }

            }
        }

        System.out.println("[TRASTERO]");
        for (int i = 0; i < numTrasteros; i++){
            System.out.printf("   T"+(i+1));
        }

        for(int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++){
            Trastero trastero = trasteros[plaza];
            if(trastero != null){
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

}
