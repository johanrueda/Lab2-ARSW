# Laboratorio 2

## Parte 1
1. Check the operation of the program and run it. While this occurs, run jVisualVM and check the CPU consumption of the corresponding process. 
Why is this consumption? Which is the responsible class? 
![](https://github.com/juanmd9/Lab2-ARSW/resources/jvisual.jpg)
![](https://github.com/juanmd9/Lab2-ARSW/resources/peorCPU.jpg)


El consumo se debe a que hay un Thread que esta en la CPU pero que nunca la esta usando.
La responsable de esto es la clase Producer, ya que la mayoria del tiempo esta en modo sleep.
2. Make the necessary adjustments so that the solution uses the CPU more efficiently, taking into account that - for now - production is slow and 
consumption is fast. Verify with JVisualVM that the CPU consumption is reduced. 
![](https://github.com/juanmd9/Lab2-ARSW/resources/mejorCPU.jpg)
![](https://github.com/juanmd9/Lab2-ARSW/resources/imagen2.jpg)
3. Make the producer now produce very fast, and the consumer consumes slow. Taking into account that the producer knows a Stock limit (how many 
elements he should have, at most in the queue), make that limit be respected. Review the API of the collection used as a queue to see how to 
ensure that this limit is not exceeded. Verify that, by setting a small limit for the 'stock', there is no high CPU consumption or errors.
![](https://github.com/juanmd9/Lab2-ARSW/resources/consumeLento.jpg)
![](https://github.com/juanmd9/Lab2-ARSW/resources/stoklimit.jpg)