package modelo;

import java.util.Random;

public class Edificio {

    private static final long SEMILLA = 12345L;
    public static final int PLANTAS_GARAJE = 2;
    private final Random rand = new Random(SEMILLA);
    public String nombre;
    public Vivienda[][] viviendas;
    public int numPlantas;
    public int viviendasPorPlanta;
    public PlazaGaraje[][] garaje;
    public int plazasPorPlantaGaraje;
    public Trastero[] trasteros;
    public int numTrasteros;


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
        if (nombre.isEmpty()) {
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
        for (int i = 0; i <= planta; i++) {
            if (this.viviendasPorPlanta == planta && this.viviendas[i][puerta] != null) {
                return this.viviendas[planta][puerta];
            }
        }
        System.out.println("ERROR: Vivienda no existe");
        return null;
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
        if (this.getVivienda(planta, puerta) != null &&
                (planta >= this.numPlantas || planta < 0) &&
                (puerta >= this.viviendasPorPlanta || puerta < 0)) {
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

        for (int planta = numPlantas; planta >= 0; planta--) {
            System.out.println("Planta" + planta);

            for (int puerta = 0; puerta < viviendasPorPlanta; puerta++) {
                Vivienda vivienda = viviendas[planta][puerta];

                if (vivienda != null) {
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
                    }

                }


            }

            System.out.println();

        }

    }

    public void mostrarEdificio() {
        mostrarEstado();

        System.out.println("[GARAJE]");
        for (int i = 0; i < plazasPorPlantaGaraje; i++) {
            System.out.printf("   P" + (i + 1));
        }


        for (int planta = 0; planta < 2; planta++) {

            System.out.println("Sotano " + (planta - 2));
            for (int plaza = 0; plaza < plazasPorPlantaGaraje; plaza++) {
                PlazaGaraje plazaGaraje = garaje[planta][plaza];
                if (plazaGaraje != null) {
                    switch (plazaGaraje.estado) {
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

        System.out.println("[TRASTERO]");
        for (int i = 0; i < numTrasteros; i++) {
            System.out.printf("   T" + (i + 1));
        }

        for (int plaza = 0; plaza < numTrasteros; plaza++){
            Trastero trastero = trasteros[plaza];
            if (trastero != null) {
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

    // Union viviendas
    public boolean puedenUnirseViviendas(int planta, int puerta1, int puerta2) {
        if (puerta1 - puerta2 == 1) {
            if (getVivienda(planta, puerta1) != null && getVivienda(planta, puerta2) != null) {
                if (getVivienda(planta, puerta1).estaDisponible() && getVivienda(planta, puerta2).estaDisponible()) {
                    return true;
                }
            }

        }
        return false;
    }
    public boolean unirViviendas(int planta, int puerta1, int puerta2, String dni, Vivienda.Calidad calidad){
        if (puerta1 >= puerta2){
            puerta1 = puerta2;
            puerta2 = puerta1;
        } else{
            if (puedenUnirseViviendas(planta, puerta1, puerta2)){
                double precionuevo = getVivienda(planta, puerta1).getPrecio() + getVivienda(planta, puerta2).getPrecio();
                double metrosnuevo = getVivienda(planta, puerta1).getMetrosCuadrados() + getVivienda(planta, puerta2).getMetrosCuadrados();
                int habnuevo= getVivienda(planta, puerta1).getHabitaciones() + getVivienda(planta, puerta2).getHabitaciones();

                new Vivienda (precionuevo,metrosnuevo,habnuevo);
                //(dni, getVivienda(planta,puerta1).calidad);

            }
        }



        return true;
    }

    // Union trasteros
    public boolean puedenUnirseTrasteros(int trastero1, int trastero2){
        if (trastero1 - trastero2 == 1) {
            if (getTrastero(trastero1) != null && getTrastero(trastero2) != null) {
                if (getTrastero(trastero1).estaDisponible() && getTrastero(trastero2).estaDisponible()) {
                    return true;
                }
            }

        }
        return false;
    }
    public boolean unirTrasteros(int trastero1, int trastero2, String dniComprador){
        if (puedenUnirseTrasteros(trastero1,trastero2)){
            double precionuevo = getTrastero(trastero1).getPrecio() + getTrastero(trastero2).getPrecio();
            double metrosnuevo = getTrastero(trastero1).getMetrosCuadrados() + getTrastero(trastero2).getMetrosCuadrados();
            new Trastero (precionuevo,metrosnuevo);


        }

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

    public String listarTrasterosPorDni(String dni) {
        String detalles = "";
        if (dni == null || dni.isEmpty()) {
            return "Dni no valido";
        } else {
            for (int i = 0; i < trasteros.length; i++) {
                if (trasteros[i] != null) {
                    if (trasteros[i].getEstado() == Trastero.Estado.VENDIDO && dni.equalsIgnoreCase(trasteros[i].getDniComprador())) {
                        detalles += trasteros[i].getDetalles();
                    }
                }
            }
            if (detalles.isEmpty()) {
                detalles = "No hay detalles";
            }
            return detalles;

        }
    }


}

