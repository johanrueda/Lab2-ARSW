# Laboratorio 2

## Compile
To compile the application you need to have java and maven installed on your computer.
```
mvn package
```

## How to run the app?
To run the app copy the following command in your console.
```
java -cp target/ConcurrentPrgExercises-Highlander-Prod-Cons-1.0.jar edu.eci.arsw.highlanderism.ControlFrame
```

## Parte 1
1. Check the operation of the program and run it. While this occurs, run jVisualVM and check the CPU consumption of the corresponding process. 
Why is this consumption? Which is the responsible class? 


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/jvisual.jpg)


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/peorCPU.jpg)


R:/ El consumo se debe a que hay un Thread que esta en la CPU pero que nunca la esta usando.
La responsable de esto es la clase Producer, ya que la mayoria del tiempo esta en modo sleep.


2. Make the necessary adjustments so that the solution uses the CPU more efficiently, taking into account that - for now - production is slow and 
consumption is fast. Verify with JVisualVM that the CPU consumption is reduced. 


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/correccion.jpg)


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/correccionhilo.jpg)


3. Make the producer now produce very fast, and the consumer consumes slow. Taking into account that the producer knows a Stock limit (how many 
elements he should have, at most in the queue), make that limit be respected. Review the API of the collection used as a queue to see how to 
ensure that this limit is not exceeded. Verify that, by setting a small limit for the 'stock', there is no high CPU consumption or errors.


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/consumeLento.jpg)


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/stoklimit.jpg)


## Parte 2
 
Review the code and identify how the functionality indicated above was implemented. Given the intention of the game, an invariant should be that the sum of the life points of all players is always the same (of course, in an instant of time in which a time increase / reduction operation is not in process ). For this case, for N players, what should this value be?


R:/ El valor debe ser N*100.


Run the application and verify how the ‘pause and check’ option works. Is the invariant fulfilled?


R:/ El invarinte no se cumple.


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/punto3.jpg)

Check the operation again (click the button many times). Is the invariant fulfilled or not ?


R:/ Se realizaron cambios en el codigo para que se cumpliera el invariante.


![](https://github.com/juanmd9/Lab2-ARSW/blob/master/resources/punto5.jpg)