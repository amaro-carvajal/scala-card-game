Explicare ciertas deciciones del diseño del modelo de cartas.

En primer lugar, se implemento el trait Card que es la raiz del modelo, donde se indican ciertos metodos que toda clase/trait que extienda
Card debe cumplir. Se tomo esta decisión pues hay diferentes tipos de cartas en el modelo y es util tener dicho trait.

Luego, tanto las cartas de unidad como las de clima tienen un trait (UnitCard, WeatherCard) que extiende Card donde se indican los contratos para ciertos
metodos especificos de dichas cartas.

También, tanto las cartas de unidad como las de clima tienen una clase constructora (BasicUnitCard, BasicWeatherCard) que extienden los trait UnitCard y WeatherCard respectivamente, 
y asi heredan sus metodos especificos, y hacen override a los metodos de Card (toString, equals, hashCode).

Por último, se decidio implementar los effectos y tipos (UnitEffect, UnitType, WeatherEffect) como traits, que tienen contratos (como UnitEffect o WeatherEffect, con applyEffect()) o no (como UnitType). Se
decidio hacer esto incluso con UnitType que solo sirve como marcador, ya que asi al implementar nuevas unidades o efectos, solo necesitamos crear una nueva clase que extienda el trait base y no caer en diseños que no sean OOP.

