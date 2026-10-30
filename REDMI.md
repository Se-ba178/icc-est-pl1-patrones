## Practica Uno Programaciòn Aplicada 
*Nombre:* 
> Christian Villa

*Fecha:*
> 30/10/2026

## Backup Hecho por mi mismo 
![alt text](app/src/assents/CapturaFigma.png)

## Backup hecho por Stich IA
![alt text](app/src/assents/CapturaStich.png)


### Estructura final de las clases para la practica usando MVC(Modelo Vista Controlador)

```java
app/src/main/java/edu/ups/pap/
├── App.java
├── modelo/
│   ├── Pedido.java
│   ├── TipoProducto.java
│   ├── prototype/
│   │   ├── Prototipo.java
│   │   ├── ProductoBase.java
│   │   ├── Cafe.java
│   │   ├── Ensalada.java
│   │   ├── Pizza.java
│   │   └── RegistroPrototipos.java
│   ├── decorator/
│   │   ├── DecoradorIngrediente.java
│   │   ├── Azucar.java
│   │   ├── Crema.java
│   │   ├── Tomate.java
│   │   ├── Pollo.java
│   │   ├── Queso.java
│   │   └── Pepperoni.java
│   └── servicio/
│       └── ServicioPedidos.java
├── vista/
│   └── VentanaPedidos.java
└── controlador/
    └── ControladorPedidos.java
```
