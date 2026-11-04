# Evidencias APP1 — PedidoLab

**Repositorio:** https://github.com/Se-ba178/icc-est-pl1-patrones.git

**Integrante:**  
>Christian Villa    

**Fecha:**

>6/11/2026

| Caso | Entrada | Resultado esperado | Resultado real | Evidencia | Estado |
|---|---|---|---|---|---|
| CA01 | Clonar Café dos veces. | IDs diferentes; ambos con precio de $2.20. | Se crearon ->1 y p-2, ambos con precio de $2.20. | app/src/assents/PRUEBACA01.png | Aprobado |
| CA02 | Comparar el prototipo registrado con su copia. | Referencias distintas; el prototipo conserva su configuración. | La comparación devolvió false. Original y copia conservaron la descripción Café y el precio de $2.20. |app/src/assents/PRUEBACA02.png | Aprobado |
| CA03 | Agregar Azúcar y Crema al primer Café. | Descripción con ambos ingredientes y total de $3.90. | La descripción mostró Café + Azúcar + Crema y el precio fue $3.90. | app/src/assents/PRUEBACA03.png | Aprobado |
| CA04 | Consultar el segundo Café después de personalizar el primero. | Conserva su descripción sin ingredientes y precio de $2.20. | El segundo pedido conservó la descripción Café y el precio de $2.20. | app/src/assents/PRUEBACA04.png | Aprobado |
| CA05 | Clonar Ensalada y agregar Tomate y Pollo. | Descripción con ambos ingredientes y total de $5.60. | La descripción mostró Ensalada sencilla + Tomate + Pollo y el precio fue $5.60. | app/src/assents/PRUEBACA05.png | Aprobado |
| CA06 | Clonar Pizza y agregar Queso y Pepperoni. | Descripción con ambos ingredientes y total de $7.10. | La descripción mostró Pizza sencilla + Queso + Pepperoni y el precio fue $7.10. | app/src/assents/PRUEBACA06.png | Aprobado |
| CA07 | Intentar agregar Queso a Café. | Operación rechazada sin cambiar descripción ni precio. | Se mostró el mensaje de incompatibilidad. El café conservó su descripción y precio de $2.20. | app/src/assents/PRUEBACA07.png | Aprobado |
| CA08 | Agregar Azúcar sin seleccionar un pedido. | Mensaje de error y ningún cambio en los pedidos. | Se solicitó seleccionar un pedido. Se mantuvo un café sin ingredientes con precio de $2.20. | app/src/assents/PRUEBACA08.png | Aprobado |
| CA09 | Solicitar la clave hamburguesa. | Error controlado; no se crea un pedido. | Se rechazó la clave inexistente. La cantidad de pedidos permaneció en 0. | app/src/assents/PRUEBACA09.png | Aprobado |
| CA10 | Agregar Azúcar dos veces a un Café. | Dos aplicaciones de azúcar y total de $3.20. | La descripción mostró Café + Azúcar + Azúcar y el precio fue $3.20. | app/src/assents/PRUEBACA10.png | Aprobado |
| CA11 | Reiniciar los pedidos y volver a clonar Café. | Lista vacía tras reiniciar; nuevo café con precio base de $2.20. | La lista quedó en 0 pedidos. Después se creó p-2 con descripción Café y precio de $2.20. | app/src/assents/PRUEBACA11.png | Aprobado |
## Identificación de patrones

- **Prototype:** Para el patron prototype creamos una carpeta en la cual abria todo lo q incluiria en dicho patron con las clases q son Cafe,Ensalada,Pizza, El productoBase y Registro de Producto los cuales Cafe,Pizza y Ensalad ason los prodcutos principales que vamos a tener . 

- **Decorator:**Como el patron prototype tambien tenemos una carpeta  q se va a destinar a todos lo q es del patron decorator con su clase abtracta q es la de DecoradorIngrediente  y las demas que vana hacer decoradores que acompañen a nuestros prodcutos principales 

- **Integración:** método de servicio donde un clon recibe al menos dos decoradores.

## Decisiones de diseño

1. En este proyecto ademas de las clases dadas yo le separa para trabajar con el MVC con la carpeta models la q su contenido era la de prototype y decorator los dos patrones que nos ayudaran a este proyecto 

2. Aparte d todas las clases tambien se implemento un metodo que se especificaba en la gua q es el BigDecimal el cual hiba a ser encargado de sumar y dar los precios finales suando de forma decimal como lo indicaba en la guia 

## Conclusiones

1. El decorator nos ayudara a los prodcutos base poderles agregar sus topinks o algo relacionado a el producto original

2. El prototype nos sirve para poder crear un objeto y despues ese producto duplicarlo para poderlo usar en nuestra aplicacion de un forma mas rapida .

3. Las pruebas en consola ayudaron a detectar errores antes de implementar la interfaz, como no guardar el pedido decorado en la lista.
