# Sistema de Gestion Cinemar

##PARA VER INTERFAZ GRAFICA CINEMAR/SRC/REGISTRO-LOGIN-INTERFAZ entrar desde APACHENETBEANS

## Contexto
Cinemar es una empresa que se dedica a proyectar películas esencialmente
dedicadas al público adolescente.
El cine cuenta con una cantidad de salas con diferentes capacidades (siendo esta
capacidad la cantidad de butacas), también dispone de salas 2D como 3D variando el precio
de las entradas.
Cuando un cliente se presenta en ventanilla muestra su tarjeta de descuento, si la
tiene, se le efectúa un descuento en el valor de la entrada, sino pueden solicitar una sí
acudieron al menos 6 veces en 3 meses, en caso contrario el precio de la entrada no tendrá
descuento alguno.
Actualmente la tabla de descuentos para los que tienen la tarjeta de descuentos es
la siguiente:
- Lunes y Miércoles: 20%
- Martes y Jueves: 15%
- Viernes, Sábados y Domingos: 10%
Siendo modificable según los directivos.
## Problemática
Los directivos de Cinemar comentaron a nuestro equipo que no cuentan con un
control de los clientes, para realizar reservas de butacas y otorgarles descuentos para
aquellos que son más recurrentes de forma automática.
Todo se efectúa mediante ventanilla y a mano, lo que provoca que en algunas salas a
veces se terminan vendiendo más entradas que la capacidad de la sala, y perdiendo ventas
en funciones por no contar con reservas por página web en horarios específicos.
## Solución
Nos llega desde la administración del cine a nuestro equipo de desarrolladores que
tenemos que implementar una solución que nos permita lo siguiente.
### Para el cliente:
- Registrarse.
- Iniciar sesión.
- Crear una reserva.
- Modificar una reserva.
- Observar mis reservas.
- Ver el histórico de mis entradas.
### Para la Administración:
- Ver reservas de todos los clientes.
- Ver reservas de un cliente en particular.
- Crear una sala con la película.
- Modificar una sala.
- Eliminar una sala.
- Modificar descuentos.
### Troncales
- Ver salas
## Consideraciones
- No se vencerán las películas, sino que será por la creación de una sala.
- La reserva implica el pago de la entrada.
- Las reservas solo se pueden modificar siempre y cuando se hagan antes de la
función.
- Las funciones son todos los días.

## Autores 👩👨

 Está integrado por los siguientes desarrolladores:
* ** Marcelo Salas** - [marcesalas96](https://github.com/marcesalas96)
* ** Martin Fradejas** - [TinchoARS](https://github.com/TinchoARS)
* ** Jorge Reales** - 
* ** Daniel Calle** - [Dany](https://github.com/FDany)
* **Jesica Martínez** - [Jesica56](https://github.com/Jesica56)

## Licencia 📄

Este proyecto está bajo la Licencia Los Devs mas rapido del O. Todos los derechos Reservados. Julio 2022.


---
