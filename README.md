# Fundamentos de Programación II — Curso 2025/2026
# SISTEMA DE GESTIÓN DE UNA PROMOTORA INMOBILIARIA
<details>
<summary> FASE 1: CLASES BÁSICAS </summary>
<details>
  
<summary> 1. INTRODUCCIÓN </summary>

### 1.1. Descripción del Problema

Una promotora inmobiliaria necesita un sistema informático para gestionar sus múltiples edificios residenciales. Cada edificio contiene diferentes tipos de propiedades: viviendas en las plantas superiores, plazas de garaje en los sótanos y trasteros.

La promotora requiere poder:

- Registrar y visualizar el estado de todas las propiedades (libres, reservadas, vendidas)
- Gestionar la venta y reserva de viviendas con diferentes niveles de calidad
- Vender plazas de garaje y trasteros individualmente
- Unir propiedades contiguas para crear espacios más grandes
- Buscar propiedades disponibles según diversos criterios (precio, superficie, habitaciones, tamaño)
- Consultar todas las propiedades de un comprador específico
- Generar estadísticas e informes sobre ingresos y disponibilidad
- Guardar y recuperar toda la información del sistema

El sistema se desarrollará siguiendo una metodología incremental, comenzando por las clases más básicas (las propiedades individuales) y avanzando progresivamente hacia niveles de mayor complejidad (edificios, promotora e interfaz de usuario).

### 1.2. Sobre este Documento

Este documento describe la implementación de las clases básicas del sistema de gestión de promotora inmobiliaria. Se enfoca en las tres clases fundamentales que representan las propiedades individuales: **Vivienda**, **PlazaGaraje** y **Trastero**.

### 1.3. Objetivos de esta Fase

- Implementar las clases básicas de propiedades con sus atributos esenciales
- Desarrollar métodos getter y setter para acceso controlado a los atributos.
- Crear métodos básicos de consulta y validación.
- Implementar la funcionalidad de venta y liberación de propiedades.
</details>

<details>
  
<summary> 2. CLASE VIVIENDA </summary>

### 2.1. Estructura de la Clase

La clase **Vivienda** representa una unidad habitacional con sus características y estado de venta.

#### 2.1.1. Enumeraciones

**Estado:** Define los posibles estados de una vivienda

- **LIBRE:** La vivienda está disponible para venta o reserva
- **RESERVADO:** La vivienda ha sido reservada por un comprador
- **VENDIDO:** La vivienda ha sido vendida

**Calidad:** Define los niveles de calidad con la que se va a construir la vivienda, con su impacto en el precio

- **ESTANDAR:** Multiplicador 1.0 (sin incremento)
- **PLUS:** Multiplicador 1.05 (+5% sobre precio base)
- **DE_LUXE:** Multiplicador 1.10 (+10% sobre precio base)

#### 2.1.2. Atributos Principales

| Atributo | Tipo | Descripción |
|---|---|---|
| estado | Estado | Estado actual de la vivienda (LIBRE, RESERVADO, VENDIDO) |
| precio | double | Precio base de la vivienda en euros |
| metrosCuadrados | double | Superficie de la vivienda en metros cuadrados |
| habitaciones | int | Número de habitaciones |
| dniComprador | String | DNI del comprador (null si no está vendida/reservada) |
| calidad | Calidad | Nivel de calidad aplicado (null si no se ha especificado) |

### 2.2. Constructor

El constructor inicializa una vivienda con valores por defecto y los parámetros proporcionados.

**Sintaxis:** `public Vivienda(double precio, double metrosCuadrados, int habitaciones)`

**Parámetros:**

- `precio`: Precio base en euros
- `metrosCuadrados`: Superficie en m²
- `habitaciones`: Número de habitaciones

**Valores inicializados:**

- `estado` se establece en `LIBRE`
- `dniComprador` se establece en `null`
- `calidad` se establece en `null`

### 2.3. Métodos Básicos

#### 2.3.1. Getters y Setters

Los métodos getter y setter permiten el acceso controlado a los atributos de la clase.

- `getEstado()` / `setEstado(Estado)`: Obtiene/establece el estado de la vivienda
- `getPrecio()` / `setPrecio(double)`: El getter devuelve el precio con el multiplicador de calidad aplicado. El setter modifica el precio base
- `getPrecioBase()`: Devuelve el precio base sin aplicar multiplicador de calidad
- `getMetrosCuadrados()` / `setMetrosCuadrados(double)`: Obtiene/establece la superficie en m²
- `getHabitaciones()` / `setHabitaciones(int)`: Obtiene/establece el número de habitaciones
- `getDniComprador()` / `setDniComprador(String)`: Obtiene/establece el DNI del comprador
- `getCalidad()` / `setCalidad(Calidad)`: Obtiene/establece el nivel de calidad

#### 2.3.2. Métodos de Consulta

`cumpleSuperficie(double metrosMin, double metrosMax)`:

- Verifica si la superficie está dentro del rango especificado
- Retorna: `boolean` (`true` si cumple, `false` en caso contrario)

`cumplePrecio(double precioMin, double precioMax)`:

- Verifica si el precio (con calidad aplicada) está dentro del rango
- Retorna: `boolean`

`cumpleHabitaciones(int habMin, int habMax)`:

- Verifica si el número de habitaciones está dentro del rango
- Retorna: `boolean`

`estaDisponible()`:

- Verifica si la vivienda está en estado `LIBRE`
- Retorna: `boolean` (`true` si está `LIBRE`)

#### 2.3.3. Métodos de Transacción

`vender(String dni, Calidad calidad)`:

- Marca la vivienda como `VENDIDA` con el DNI y calidad especificados
- Valida que el DNI no sea nulo o vacío
- Retorna: `boolean` (`true` si la operación fue exitosa)

`vender(String dni)`:

- Versión sobrecargada que vende con calidad `ESTANDAR` por defecto
- Llama internamente a `vender(dni, Calidad.ESTANDAR)`

`reservar(String dni, Calidad calidad)`:

- Marca la vivienda como `RESERVADA`
- Funciona de manera similar a `vender()` pero cambia el estado a `RESERVADO`

`reservar(String dni)`:

- Versión sobrecargada que reserva con calidad `ESTANDAR`

`liberar()`:

- Restaura la vivienda al estado `LIBRE`
- Elimina el DNI del comprador (lo establece en `null`)
- Elimina la calidad aplicada (la establece en `null`)

#### 2.3.4. Métodos de Representación

`toString()`:

- Retorna una representación simple del estado: `'L'` (libre), `'R'` (reservado), `'V'` (vendido)

`getDetalles()`:

- Retorna información completa de la vivienda en formato `String`
- Incluye: estado, precio (con calidad), superficie, habitaciones
- Si tiene calidad asignada, muestra el nombre de la calidad
- Si está vendida/reservada, muestra el DNI del comprador
</details>

<details>
  
<summary> 3. CLASE PLAZAGARAJE </summary>

### 3.1. Estructura de la Clase

La clase **PlazaGaraje** representa una plaza de aparcamiento en el garaje del edificio.

#### 3.1.1. Enumeraciones

**Estado:** Define los posibles estados de una plaza de garaje (solo dos estados)

- **LIBRE:** La plaza está disponible para venta
- **VENDIDO:** La plaza ha sido vendida

#### 3.1.2. Atributos Principales

| Atributo | Tipo | Descripción |
|---|---|---|
| estado | Estado | Estado actual de la plaza (LIBRE o VENDIDO) |
| precio | double | Precio de la plaza en euros |
| metrosCuadrados | double | Superficie de la plaza en metros cuadrados |
| dniComprador | String | DNI del comprador (null si no está vendida) |
| UMBRAL_GRANDE | double | Constante con valor 12.0 - umbral para clasificar tamaño |

### 3.2. Constructor

**Sintaxis:** `public PlazaGaraje(double precio, double metrosCuadrados)`

**Parámetros:**

- `precio`: Precio de la plaza en euros
- `metrosCuadrados`: Superficie en m²

**Valores inicializados:**

- `estado` se establece en `LIBRE`
- `dniComprador` se establece en `null`

### 3.3. Métodos Básicos

#### 3.3.1. Getters y Setters

Métodos similares a los de Vivienda pero sin calidad ni habitaciones:

- `getEstado()` / `setEstado(Estado)`: Obtiene/establece el estado
- `getPrecio()` / `setPrecio(double)`: Obtiene/establece el precio
- `getMetrosCuadrados()` / `setMetrosCuadrados(double)`: Obtiene/establece la superficie
- `getDniComprador()` / `setDniComprador(String)`: Obtiene/establece el DNI

#### 3.3.2. Métodos de Consulta

`esGrande()`:

- Verifica si la plaza supera el umbral de 12.0 m²
- Retorna: `boolean` (`true` si `metrosCuadrados > 12.0`)

`cumpleSuperficie(double metrosMin, double metrosMax)`:

- Verifica si la superficie está dentro del rango

`cumplePrecio(double precioMin, double precioMax)`:

- Verifica si el precio está dentro del rango

`cumpleTamano(int filtroTamano)`:

- Verifica si la plaza cumple con el filtro de tamaño especificado
- `filtroTamano = 0`: cualquier tamaño
- `filtroTamano = 1`: plazas pequeñas (≤12 m²)
- `filtroTamano = 2`: plazas grandes (>12 m²)

`estaDisponible()`:

- Verifica si la plaza está en estado `LIBRE`

#### 3.3.3. Métodos de Transacción

`vender(String dni)`:

- Marca la plaza como `VENDIDA`
- Valida que el DNI no sea nulo o vacío
- Retorna: `boolean`

`liberar()`:

- Restaura la plaza al estado `LIBRE` y elimina el DNI

#### 3.3.4. Métodos de Representación

- `toString()`: Retorna `'L'` o `'V'`
- `getDetalles()`: Retorna información completa incluyendo si es grande o pequeña
</details>

<details>
  
<summary> 4. CLASE TRASTERO </summary>

### 4.1. Estructura de la Clase

La clase **Trastero** es muy similar a PlazaGaraje pero con un umbral diferente para clasificar el tamaño.

#### 4.1.1. Atributos Principales

| Atributo | Tipo | Descripción |
|---|---|---|
| estado | Estado | Estado actual del trastero (LIBRE o VENDIDO) |
| precio | double | Precio del trastero en euros |
| metrosCuadrados | double | Superficie del trastero en metros cuadrados |
| dniComprador | String | DNI del comprador (null si no está vendido) |
| UMBRAL_GRANDE | double | Constante con valor 7.0 - umbral para clasificar tamaño |

### 4.2. Diferencias con PlazaGaraje

La clase **Trastero** tiene la misma estructura y métodos que PlazaGaraje, con una única diferencia importante:

- `UMBRAL_GRANDE = 7.0`: Un trastero se considera grande si supera los 7.0 m² (en lugar de 12.0 m² de las plazas)

### 4.3. Métodos

Todos los métodos son idénticos a los de PlazaGaraje:

- Constructor, getters y setters
- `esGrande()` - usa el umbral de 7.0 m²
- `cumpleSuperficie()`, `cumplePrecio()`, `cumpleTamano()`
- `estaDisponible()`
- `vender()`, `liberar()`
- `toString()`, `getDetalles()`
</details>

<details>
  
<summary> 5. ORDEN DE IMPLEMENTACIÓN </summary>

### 5.1. Paso 1: Crear la Estructura Básica

- Declarar el paquete (`package modelo`)

### 5.2. Paso 2: Definir las Enumeraciones

**Para Vivienda:**

- Crear `enum Estado` con `LIBRE`, `RESERVADO`, `VENDIDO`
- Crear `enum Calidad` con multiplicador y nombre

**Para PlazaGaraje y Trastero:**

- Crear `enum Estado` con `LIBRE`, `VENDIDO`

### 5.3. Paso 3: Declarar los Atributos

- Declarar todos los atributos como `private`
- Incluir las constantes (`UMBRAL_GRANDE`)

### 5.4. Paso 4: Implementar el Constructor

- Crear el constructor con los parámetros necesarios
- Inicializar todos los atributos con sus valores por defecto

### 5.5. Paso 5: Crear Getters y Setters

- Implementar los métodos `get` y `set` para cada atributo
- Para Vivienda: implementar `getPrecio()` con lógica de calidad

### 5.6. Paso 6: Implementar Métodos de Consulta

- Implementar `cumpleSuperficie()`, `cumplePrecio()`
- Para Vivienda: implementar `cumpleHabitaciones()`
- Para PlazaGaraje y Trastero: implementar `esGrande()` y `cumpleTamano()`
- Implementar `estaDisponible()`

### 5.7. Paso 7: Implementar Métodos de Transacción

- Implementar `vender()` con validación de DNI
- Para Vivienda: implementar las versiones sobrecargadas y `reservar()`
- Implementar `liberar()`

### 5.8. Paso 8: Implementar Métodos de Representación

- Implementar `toString()` con `@Override`
- Implementar `getDetalles()`
</details>

<details>
  
<summary> 6. PUNTOS CLAVE DE ESTA FASE </summary>

### 6.1. Conceptos Importantes

- **Encapsulado:** Todos los atributos son privados y se acceden mediante getters/setters
- **Enumeraciones:** Uso de enums para estados y calidades con métodos asociados
- **Validación:** Comprobación del DNI antes de vender o reservar

### 6.2. Próxima Fase

Una vez completadas estas tres clases básicas, en la siguiente fase se implementará:

- Clase `Edificio`: contendrá arrays de viviendas, plazas y trasteros
- Métodos de inicialización y generación aleatoria
- Visualización del estado del edificio
</details>
</details>
<details>
<summary> FASE 2: CLASE EDIFICIO </summary>
<details>

  
<summary> 1. INTRODUCCIÓN </summary>

Este documento describe la implementación de la clase Edificio, que representa el nivel
intermedio de complejidad del sistema. Un edificio gestiona colecciones de viviendas,
plazas de garaje y trasteros, utilizando las clases básicas desarrolladas en la Fase 1.

## 1.1. Requisitos Previos

Antes de implementar esta clase, deben estar completadas las clases:
• Vivienda (con sus enums Estado y Calidad)
• PlazaGaraje
• Trastero

## 1.2. Objetivos de esta Fase

• Gestionar arrays bidimensionales (viviendas, garaje) y unidimensionales
(trasteros)
• Implementar métodos de inicialización y generación de datos
• Crear visualizaciones del estado del edificio
• Desarrollar funciones de búsqueda y filtrado
• Implementar operaciones complejas como unión de propiedades
</details>
<details>
  
<summary> 2. ESTRUCTURA DE LA CLASE EDIFICIO </summary>

## 2.1. Atributos Principales

### 2.1. Atributos Principales

| Atributo | Tipo | Descripción |
|---|---|---|
| `nombre` | `String` | Nombre del edificio |
| `viviendas` | `Vivienda[][]` | Matriz bidimensional `[planta][puerta]` |
| `numPlantas` | `int` | Número de plantas del edificio |
| `viviendasPorPlanta` | `int` | Número de viviendas por planta |
| `garaje` | `PlazaGaraje[][]` | Matriz bidimensional `[planta][plaza]` (2 plantas fijas) |
| `plazasPorPlantaGaraje` | `int` | Número de plazas por planta de garaje |
| `trasteros` | `Trastero[]` | Array unidimensional de trasteros |
| `numTrasteros` | `int` | Número total de trasteros |

### 2.2. Constantes

- `PLANTAS_GARAJE = 2`: El garaje siempre tiene exactamente 2 plantas (sótano -1 y sótano -2)

---

## 3. CONSTRUCTOR E INICIALIZACIÓN

### 3.1. Constructor Principal

**Sintaxis:**

```java
public Edificio(String nombre, int numPlantas, int viviendasPorPlanta,
                int plazasPorPlantaGaraje, int numTrasteros)
```

**Acciones del constructor:**

1. Asignar los parámetros a los atributos correspondientes
2. Inicializar la matriz de viviendas: `new Vivienda[numPlantas][viviendasPorPlanta]`
3. Inicializar la matriz del garaje: `new PlazaGaraje[2][plazasPorPlantaGaraje]`
4. Inicializar el array de trasteros: `new Trastero[numTrasteros]`
5. Llamar a `generarViviendasAleatorias()`
6. Llamar a `generarGarajeAleatorio()`
7. Llamar a `generarTrasterosAleatorios()`

### 3.2. Generación Aleatoria Automática

**IMPORTANTE:** El constructor llama automáticamente a los métodos de generación aleatoria. No es necesario inicializar con valores por defecto ni llamar manualmente a métodos de generación.

#### 3.2.1. `generarViviendasAleatorias()`

**Propósito:** Generar viviendas con características aleatorias realistas.

**Lógica:**

- Crear objeto `Random` con semilla `12345L`
- Recorrer todas las plantas y puertas
- Para cada posición, generar valores aleatorios dentro de rangos realistas

**Rangos de valores:**

- Precio: `80000 + (planta * 10000) + aleatorio(0-120000)` euros
- Metros cuadrados: `40` a `180` m²
- Habitaciones: `1` a `5`

#### 3.2.2. `generarGarajeAleatorio()`

**Propósito:** Generar plazas de garaje con características aleatorias.

**Rangos de valores:**

- Precio: `8000` a `30000` euros
- Metros cuadrados: `8` a `20` m²

#### 3.2.3. `generarTrasterosAleatorios()`

**Propósito:** Generar trasteros con características aleatorias.

**Rangos de valores:**

- Precio: `1500` a `8000` euros
- Metros cuadrados: `3` a `15` m²

---

## 4. MÉTODOS DE ACCESO BÁSICOS

### 4.1. Getters Generales

- `getNombre()` / `setNombre(String)`: Obtiene/establece el nombre del edificio
- `getNumPlantas()`: Retorna el número de plantas
- `getViviendasPorPlanta()`: Retorna el número de viviendas por planta
- `getPlantasGaraje()`: Retorna la constante `2`
- `getPlazasPorPlantaGaraje()`: Retorna el número de plazas por planta
- `getNumTrasteros()`: Retorna el número de trasteros

### 4.2. Métodos de Acceso a Propiedades

#### `getVivienda(int planta, int puerta)`

- Valida que los índices estén dentro de los límites
- Retorna la `Vivienda` en la posición especificada o `null` si no es válida

#### `setVivienda(int planta, int puerta, Vivienda vivienda)`

- Valida índices antes de asignar
- Útil para operaciones de unión de viviendas

Los métodos `getPlazaGaraje(int sotano, int plaza)` y `getTrastero(int indice)` funcionan de manera similar.

---

## 5. MÉTODOS DE VISUALIZACIÓN

### 5.1. `mostrarEstado()`

**Propósito:** Mostrar una vista tabular de las viviendas del edificio.

**Formato de salida:**

- Título con nombre del edificio y leyenda
- Cabecera con números de puerta
- Plantas mostradas de arriba a abajo (de mayor a menor)
- Estado de cada vivienda usando `toString()` → `L / R / V`

Las plantas `3` y `2` tienen `4` viviendas (una unión de viviendas cada una), el resto tienen `5`.

> En el PDF aparece una imagen de ejemplo con una salida por consola del estado del edificio.

### 5.2. `mostrarMatrizEdificio()`

**Propósito:** Mostrar una vista completa del edificio incluyendo viviendas, garaje y trasteros.

**Incluye tres secciones:**

8. **VIVIENDAS**: Matriz de plantas y puertas  
9. **GARAJE**: Matriz de sótanos y plazas  
10. **TRASTEROS**: Array lineal de trasteros  

> En el PDF aparece una imagen de ejemplo con una salida completa por consola que muestra las tres secciones.

---

## 6. MÉTODOS DE CONTEO Y ESTADÍSTICAS

Estos métodos proporcionan información cuantitativa sobre el estado del edificio.

### 6.1. Métodos de Conteo de Viviendas

- `contarViviendasLibres()`: Cuenta viviendas en estado `LIBRE`
- `contarViviendasReservadas()`: Cuenta viviendas en estado `RESERVADO`
- `contarViviendasVendidas()`: Cuenta viviendas en estado `VENDIDO`
- `getTotalViviendas()`: Cuenta todas las viviendas no nulas

### 6.2. Métodos de Cálculo de Ingresos

- `calcularIngresosPotenciales()`: Suma los precios de todas las viviendas (vendidas o no)
- `calcularIngresosVendidos()`: Suma los precios solo de viviendas `VENDIDAS`

**Nota importante:** Los métodos de conteo e ingresos tienen versiones equivalentes para plazas de garaje y trasteros.

---

## 7. MÉTODOS DE BÚSQUEDA Y FILTRADO

Los métodos de búsqueda permiten encontrar propiedades que cumplan ciertos criterios.

### 7.1. Búsqueda de Viviendas por Criterio Individual

#### `buscarViviendasPorSuperficie(double metrosMin, double metrosMax)`

- Recorre todas las viviendas del edificio
- Usa el método `cumpleSuperficie()` de cada vivienda
- Muestra las que están disponibles y cumplen el criterio

#### `buscarViviendasPorPrecio(double precioMin, double precioMax)`

- Similar al anterior, pero filtra por precio

#### `buscarViviendasPorHabitaciones(int habMin, int habMax)`

- Filtra por número de habitaciones

### 7.2. Búsqueda Combinada de Viviendas

#### `buscarViviendas(double metrosMin, double metrosMax, double precioMin, double precioMax, int habMin, int habMax)`

- Busca viviendas que cumplan **TODOS** los criterios simultáneamente
- Usa los tres métodos `cumple...()` de la clase `Vivienda`

### 7.3. Búsqueda de Plazas de Garaje

Métodos análogos para plazas de garaje:

- `buscarPlazasGarajePorSuperficie(metrosMin, metrosMax)`
- `buscarPlazasGarajePorPrecio(precioMin, precioMax)`
- `buscarPlazasGarajePorTamano(filtroTamano)`  
  donde `filtroTamano` puede ser:
  - `0` → todas
  - `1` → pequeñas
  - `2` → grandes
- `buscarPlazasGaraje(...)` → búsqueda combinada

### 7.4. Búsqueda de Trasteros

Métodos análogos para trasteros (misma estructura que plazas de garaje).

---

## 8. OPERACIONES COMPLEJAS

### 8.1. Unión de Viviendas

#### 8.1.1. `puedenUnirseViviendas(int planta, int puerta1, int puerta2)`

**Propósito:** Verificar si dos viviendas pueden unirse.

**Condiciones que deben cumplirse:**

11. Las puertas deben ser contiguas: `|puerta1 - puerta2| == 1`
12. Ambas viviendas deben existir (`no null`)
13. Ambas deben estar disponibles (`estado LIBRE`)

#### 8.1.2. `unirViviendas(int planta, int puerta1, int puerta2, String dni, Vivienda.Calidad calidad)`

**Propósito:** Unir dos viviendas contiguas en una sola.

**Proceso:**

14. Asegurar que `puerta1 < puerta2` (intercambiar si es necesario)
15. Verificar que pueden unirse (llamar a `puedenUnirseViviendas`)
16. Sumar: precio, metros cuadrados y habitaciones de ambas viviendas
17. Crear una nueva `Vivienda` con los valores sumados
18. Vender la nueva vivienda al comprador con la calidad especificada
19. Colocar la vivienda unida en `puerta1`
20. Desplazar todas las viviendas a la izquierda a partir de `puerta2`
21. Establecer la última posición en `null`
22. Retornar `true` si todo fue exitoso

### 8.2. Unión de Trasteros

La unión de trasteros funciona de manera muy similar a la unión de viviendas:

#### `puedenUnirseTrasteros(int trastero1, int trastero2)`

- Verifica que sean contiguos y que ambos existan

#### `unirTrasteros(int trastero1, int trastero2, String dniComprador)`

- Suma precio y metros cuadrados
- Crea nuevo trastero unido y lo marca como `VENDIDO`
- Desplaza el array hacia la izquierda

---

## 9. MÉTODOS DE CONSULTA POR DNI

Estos métodos permiten encontrar todas las propiedades de un comprador específico.

### 9.1. Métodos de Conteo

- `contarViviendasPorDni(String dni)`: Cuenta cuántas viviendas tiene un comprador
- `contarPlazasPorDni(String dni)`: Cuenta plazas de garaje del comprador
- `contarTrasterosPorDni(String dni)`: Cuenta trasteros del comprador

### 9.2. Métodos de Listado

- `listarViviendasPorDni(String dni)`: Muestra detalles de todas las viviendas del comprador
- `listarPlazasPorDni(String dni)`: Muestra detalles de plazas de garaje del comprador
- `listarTrasterosPorDni(String dni)`: Muestra detalles de trasteros del comprador

**Implementación típica:**

23. Recorrer todas las propiedades del tipo correspondiente
24. Comparar el DNI usando `equalsIgnoreCase()` para no distinguir mayúsculas
25. Mostrar o contar según corresponda

---

## 10. ORDEN DE IMPLEMENTACIÓN RECOMENDADO

### 10.1. Fase A: Estructura Básica

26. Declarar la clase y los atributos  
27. Implementar el constructor  
28. Implementar métodos de generación aleatoria  
29. Implementar getters básicos  

### 10.2. Fase B: Acceso a Propiedades

30. Implementar `getVivienda`, `getPlazaGaraje`, `getTrastero`  
31. Implementar `setVivienda`  

### 10.3. Fase C: Visualización

32. Implementar `mostrarEstado` (solo viviendas)  
33. Implementar `mostrarMatrizEdificio` (completo)  

### 10.4. Fase D: Métodos de Conteo

34. Implementar contadores de viviendas (libres, reservadas, vendidas, total)  
35. Implementar contadores de plazas  
36. Implementar contadores de trasteros  

### 10.5. Fase E: Cálculo de Ingresos

37. Implementar cálculos de ingresos para viviendas  
38. Implementar cálculos para plazas  
39. Implementar cálculos para trasteros  

### 10.6. Fase F: Búsqueda y Filtrado

40. Implementar búsquedas individuales de viviendas  
41. Implementar búsqueda combinada de viviendas  
42. Implementar búsquedas de plazas  
43. Implementar búsquedas de trasteros  

### 10.7. Fase G: Consultas por DNI

44. Implementar contadores por DNI  
45. Implementar listados por DNI  

### 10.8. Fase H: Operaciones Complejas

46. Implementar `puedenUnirseViviendas`  
47. Implementar `unirViviendas`  
48. Implementar `puedenUnirseTrasteros`  
49. Implementar `unirTrasteros`  

---

## 11. PUNTOS CLAVE DE ESTA FASE

### 11.1. Conceptos Importantes

- **Gestión de Arrays:** Manejo de matrices bidimensionales y arrays unidimensionales
- **Validación de Índices:** Verificación de límites antes de acceder a arrays
- **Desplazamiento de Arrays:** Técnica para eliminar elementos manteniendo el orden

### 11.2. Próxima Fase

Una vez completada la clase `Edificio`, en la siguiente fase se implementará:

- **Clase `Promotora`**: gestiona múltiples edificios
- Operaciones a nivel de promotora
- **Clase `Main`**: interfaz de usuario

</details>
</details>
