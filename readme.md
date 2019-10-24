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

    
    