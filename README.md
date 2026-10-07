# Scala Card Game

Proyecto académico desarrollado en **Scala** para el curso
**Metodologías de Diseño y Programación** de la Universidad de Chile.

El proyecto implementa la lógica de un juego de cartas, con énfasis en
programación orientada a objetos, diseño extensible, patrones de diseño
y testing automatizado.

## Tecnologías

- Scala
- sbt
- Git

## Conceptos aplicados

- Programación orientada a objetos
- Arquitectura MVC
- Patrón State
- Patrón Observer
- Traits, herencia y polimorfismo
- Testing automatizado

## Arquitectura

El proyecto separa la lógica principal en distintos componentes:

- `model/`: representa las cartas, jugadores, tablero y demás elementos
  del dominio del juego.
- `controller/`: contiene `GameController` y la lógica que coordina el
  flujo de una partida.
- `controller/state/`: implementa los distintos estados del juego
  mediante el patrón State.
- `observer/`: contiene la implementación del patrón Observer.
- `src/test/`: contiene las pruebas automatizadas de los principales
  componentes del sistema.

## Diseño del modelo de cartas

El modelo utiliza el trait `Card` como raíz de la jerarquía de cartas,
definiendo el comportamiento común de los distintos tipos.

Las cartas de unidad y clima se especializan mediante `UnitCard` y
`WeatherCard`, permitiendo definir comportamientos específicos sin
perder una interfaz común.

Los efectos y tipos de unidades también se modelan mediante traits,
como `UnitEffect`, `UnitType` y `WeatherEffect`. Esto permite incorporar
nuevos tipos de cartas y efectos manteniendo una estructura modular y
extensible.

## Patrones de diseño

### State

El flujo de la partida se modela mediante distintos estados, entre ellos:

- `InitializingState`
- `PlayerTurnState`
- `MachineTurnState`
- `CalculatingScoreState`
- `RoundEndState`
- `FinalState`

Esto permite encapsular el comportamiento correspondiente a cada etapa
de la partida y separar las transiciones de estado de la lógica general
del controlador.

### Observer

El proyecto incorpora el patrón Observer mediante las abstracciones
`Subject` y `Observer`, permitiendo desacoplar la notificación de cambios
entre componentes.

## Testing

El proyecto cuenta con pruebas automatizadas para distintos componentes
del modelo y controlador, incluyendo:

- Cartas de unidad y clima
- Tablero y filas de combate
- Jugadores y mazos
- `GameController`
- Estados de la partida

## Contexto

Proyecto desarrollado como parte del curso **Metodologías de Diseño y
Programación (CC3002)** de la Universidad de Chile.
