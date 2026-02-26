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
