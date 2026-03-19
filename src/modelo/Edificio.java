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
    public void contarViviendasPorDni(String dni){
        //

    }
    public void contarPlazasPorDni(String dni){
        // Si la plaza tiene asociada el mismo dni se le guarda al comprador la plaza
    }
    public void contarTrasterosPorDni(String dni){
    }

    // Metodos de Listado
    public String listarViviendasPorDni(String dni){
        return ".";
    }
    public String listarPlazasPorDni(String dni){
        return ".";
    }
    public String listarTrasterosPorDni(String dni){
        return ".";
    }

}
