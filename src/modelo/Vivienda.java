package modelo;

enum Estado_VIVIENDA {
    LIBRE,
    RESERVADO,
    VENDIDO
}
enum Calidad_VIVIENDA {
    ESTANDAR,
    PLUS,
    DE_LUXE

}

public class Vivienda {

     Estado_VIVIENDA estado = Estado_VIVIENDA.LIBRE;
     double precio;
     double metrosCuadrados;
     int habitaciones;
     String dniComprador = null;
     Calidad_VIVIENDA calidad = null;


}