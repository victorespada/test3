# TEST 3 - ITERIAM

## Prueba para ITERIAM / SANITAS

### 1 Primera aproximacion

+ Descargo el proyecto y lo añado a un proyecto de github (https://github.com/victorespada/test3.git) en rama master. 
+ A partir de ahora vamos a ver trabajar en la rama develop. 
+ Tras mirar veo cosas que me gustaría cambiar:
    - La clase Enum la voy a dejar solo para identificar navegadores.
    - Voy a crear una factoria para los browser
    - Para arreglar los test vere como simularlo.
        
### 2 Factoria

+ Genero una factoria para el browsermanager y dejo el Enum solo para definir el nombre, quitandole toda la logica
+ Modifico el test para hacerlo compatible
+ Ha sido necesario subir a java8 (switch con cadenas, en el 7 no funcionaba) para la factoria, quería hacerla con el
     enum, pero me gustaría seguir permitiendo el "test" y "perry" a la hora de llamar a la factoria...
+ Actualizado el pom para lombok
    
### 3 Tests
    
- Tras adaptarlo y quitar lo que sobraba veo que falla, hay que simular de alguna manera la llamada a getdriver, que es la que provoca los problemas.    
    
### 4 Mocks
+ Me falla sljf y quito el de lombok, se soluciona, aunque no estoy escribiendo logs...
+ Añado powermockito
+ La clase getdriver da problemas y hay que mockearla, la meto en una clase estatica, un helper

### 5 Tests
+ Pasados, con la peculiaridad de Marionette, que al ser el código igual que firefox el mock al driver se hace con la llamada a firefox.
    
# MEJORAS
+ No he usado JUnit 5 porque me estuvo dando problemas en unas pruebas con mockito, no había usado antes estas dos
herramientas con estas versiones juntas y no he sacado tiempo para ver que ocurria.
+ La factoria la veo mejor que la solución con el enum, por lo menos a futuro no sería complicado hacer
crecer el código y funcionalidades.
+ El helper he intentado meterlo dentro de las clases de la factoria, pero me daba error al mockearlo, al final creo que 
va mejor así de todos modos, aunque no era la idea que tenía al comienzo.
+ Faltan logs, traza, control de errores...
+ **_Los tests están hechos para que siempre den OK porque se supone que va a fallar en el entorno de IC. Con tiempo habría
metido algun perfil o alguna variable de entorno, seguramente perfil, para que al ejecutar el mvn clean package en el
entorno de integracion tire por los tests con mock y en local si haga las pruebas bien. Al ser algo
que se genera en integracion continua entiendo que los tests solo se van a pasar en esos dos sitios
 ya que IC será la encargada de publicar en los entornos._**
+ Existe la version 3 de mockito, no lo sabía, pero al igual que junit5 necesitaría tiempo para ver que mejoras trae 
para ver si se pueden simplificar o hacer mas sencillos los tests.
+ Los tests son la misma funciona repetida varias veces, en estos casos suelo hacer una función generica y luego
los tests llaman a ella, para no repetir código, en este caso, con los instanceof seguramente me habría dado algún problema
pero tiraría por ese camino.
+ Además de factoria otra opción habría sido hacer clases genericas, pero vi más rápido esto ahora mismo.
+ No veo ahora mismo nada más que cambiaría para mejorar, como mucho lo haría diferente.