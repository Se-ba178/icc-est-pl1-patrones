package edu.ups.pap.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ups.pap.controller.ControladorPedidos;
import edu.ups.pap.models.Pedido;

// Una sola ventana. Los botones llaman al controlador.
// Las reglas, los clones y los precios siguen en el servicio y el modelo.
public class VentanaPedidos extends JFrame
        implements ActionListener, ListSelectionListener {

    private final Color fondo = new Color(248, 245, 240);
    private final Color naranja = new Color(195, 104, 61);
    private final Color cafe = new Color(65, 43, 27);
    private final Color borde = new Color(232, 224, 215);
    private final Color suave = new Color(255, 244, 234);
    private final Color gris = new Color(130, 117, 105);

    private final ControladorPedidos controlador = new ControladorPedidos();
    private String productoElegido = "cafe";
    private String ingredienteElegido = null;

    // Arreglos: la posición de un botón coincide con su clave.
    private final String[] clavesProductos = {"cafe", "pizza", "ensalada"};
    private final String[] clavesIngredientes = {
        "azucar", "crema", "tomate", "pollo", "queso", "pepperoni"
    };
    private final JButton[] productos = new JButton[3];
    private final JButton[] ingredientes = new JButton[6];

    private JButton botonCrear;
    private JButton botonAgregar;
    private JButton botonLimpiar;
    private JButton botonReiniciar;
    private DefaultListModel<String> modeloLista;
    private JList<String> lista;
    private JLabel cantidad;
    private JLabel idDetalle;
    private JLabel tipoDetalle;
    private JTextArea descripcion;
    private JLabel total;
    private JLabel estado;

    // Evita atender cambios de selección mientras reconstruimos la lista.
    private boolean actualizandoLista = false;

    public VentanaPedidos() {
        setTitle("PedidoLab - Café & Bistro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1120, 760);
        setMinimumSize(new Dimension(1000, 680));

        JPanel principal = new JPanel(new BorderLayout(0, 0));
        principal.setBackground(fondo);
        principal.add(crearEncabezado(), BorderLayout.NORTH);

        // BorderLayout permite dar más anchura a la columna izquierda.
        JPanel contenido = new JPanel(new BorderLayout(18, 0));
        contenido.setOpaque(false);
        contenido.setBorder(BorderFactory.createEmptyBorder(18, 18, 14, 18));

        JPanel izquierda = new JPanel(new BorderLayout(0, 16));
        izquierda.setOpaque(false);
        izquierda.add(crearProductos(), BorderLayout.NORTH);
        izquierda.add(crearIngredientes(), BorderLayout.CENTER);

        JPanel derecha = new JPanel(new BorderLayout(0, 16));
        derecha.setOpaque(false);
        derecha.setPreferredSize(new Dimension(420, 0));
        derecha.add(crearPedidos(), BorderLayout.NORTH);
        derecha.add(crearDetalle(), BorderLayout.CENTER);

        contenido.add(izquierda, BorderLayout.CENTER);
        contenido.add(derecha, BorderLayout.EAST);
        principal.add(contenido, BorderLayout.CENTER);

        estado = new JLabel("Selecciona un producto y pulsa Crear pedido.");
        estado.setFont(new Font("SansSerif", Font.PLAIN, 11));
        estado.setForeground(gris);
        estado.setBorder(BorderFactory.createEmptyBorder(0, 18, 12, 18));
        principal.add(estado, BorderLayout.SOUTH);

        setContentPane(principal);
        pintarSeleccion(productos, 0);
        setLocationRelativeTo(null);
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 24, 15, 24));

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 4));
        textos.setOpaque(false);
        JLabel titulo = new JLabel("CAFÉ & BISTRO");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 21));
        titulo.setForeground(cafe);
        JLabel subtitulo = new JLabel("PedidoLab  /  Personalización de pedidos");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        subtitulo.setForeground(gris);
        textos.add(titulo);
        textos.add(subtitulo);

        JLabel estacion = new JLabel("ESTACIÓN: BARRA / CAJA");
        estacion.setForeground(cafe);
        estacion.setFont(new Font("SansSerif", Font.BOLD, 11));
        panel.add(textos, BorderLayout.WEST);
        panel.add(estacion, BorderLayout.EAST);
        return panel;
    }

    private JPanel crearProductos() {
        JPanel panel = crearSeccion("PRODUCTO", "Selecciona una base");
        panel.setPreferredSize(new Dimension(500, 250));

        // La altura de las tarjetas queda limitada; no llenan toda la ventana.
        JPanel tarjetas = new JPanel(new GridLayout(1, 3, 10, 0));
        tarjetas.setOpaque(false);
        productos[0] = crearTarjeta("CAFÉ", "Café", "$2.20");
        productos[1] = crearTarjeta("PIZZA", "Pizza sencilla", "$5.00");
        productos[2] = crearTarjeta("ENSALADA", "Ensalada sencilla", "$3.00");

        for (int i = 0; i < productos.length; i++) {
            tarjetas.add(productos[i]);
        }
        panel.add(tarjetas, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        acciones.setOpaque(false);
        botonCrear = crearBoton("+ Crear pedido", true);
        acciones.add(botonCrear);
        panel.add(acciones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearIngredientes() {
        JPanel panel = crearSeccion("INGREDIENTES Y TOPPINGS", "Elige uno y aplícalo");

        JPanel tarjetas = new JPanel(new GridLayout(2, 3, 10, 10));
        tarjetas.setOpaque(false);
        tarjetas.setPreferredSize(new Dimension(500, 190));
        ingredientes[0] = crearTarjeta("AZÚCAR", "Para café", "+$0.50");
        ingredientes[1] = crearTarjeta("CREMA", "Para café", "+$1.20");
        ingredientes[2] = crearTarjeta("TOMATE", "Para ensalada", "+$1.10");
        ingredientes[3] = crearTarjeta("POLLO", "Para ensalada", "+$1.50");
        ingredientes[4] = crearTarjeta("QUESO", "Para pizza", "+$1.00");
        ingredientes[5] = crearTarjeta("PEPPERONI", "Para pizza", "+$1.10");

        for (int i = 0; i < ingredientes.length; i++) {
            tarjetas.add(ingredientes[i]);
        }

        JPanel zona = new JPanel(new BorderLayout());
        zona.setOpaque(false);
        zona.add(tarjetas, BorderLayout.NORTH);
        panel.add(zona, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new BorderLayout(10, 0));
        acciones.setOpaque(false);
        botonLimpiar = crearBoton("Limpiar opción", false);
        botonAgregar = crearBoton("+ Agregar ingrediente", true);
        acciones.add(botonLimpiar, BorderLayout.WEST);
        acciones.add(botonAgregar, BorderLayout.EAST);
        panel.add(acciones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPedidos() {
        JPanel panel = crearSeccion("DETALLES DEL PEDIDO", "Selecciona para personalizar");
        panel.setPreferredSize(new Dimension(420, 250));
        modeloLista = new DefaultListModel<String>();
        lista = new JList<String>(modeloLista);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setFixedCellHeight(72);
        lista.setSelectionBackground(suave);
        lista.setSelectionForeground(cafe);
        lista.setForeground(cafe);
        lista.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        lista.addListSelectionListener(this);

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createLineBorder(borde));
        panel.add(scroll, BorderLayout.CENTER);
        cantidad = new JLabel("0 pedidos creados");
        cantidad.setFont(new Font("SansSerif", Font.PLAIN, 11));
        cantidad.setForeground(gris);
        panel.add(cantidad, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearDetalle() {
        JPanel panel = crearSeccion("RESUMEN DEL PEDIDO", "Importes sin impuestos");

        JPanel resumen = new JPanel(new BorderLayout(0, 12));
        resumen.setBackground(fondo);
        resumen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borde),
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        JPanel datos = new JPanel(new GridLayout(2, 1, 0, 8));
        datos.setOpaque(false);
        idDetalle = new JLabel("PEDIDO: sin seleccionar");
        tipoDetalle = new JLabel("PRODUCTO: —");
        idDetalle.setFont(new Font("SansSerif", Font.BOLD, 12));
        tipoDetalle.setFont(new Font("SansSerif", Font.PLAIN, 11));
        idDetalle.setForeground(cafe);
        tipoDetalle.setForeground(gris);
        datos.add(idDetalle);
        datos.add(tipoDetalle);

        descripcion = new JTextArea("Crea o selecciona un pedido para ver su detalle.");
        descripcion.setEditable(false);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setBackground(fondo);
        descripcion.setForeground(gris);
        descripcion.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(descripcion);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        total = new JLabel("TOTAL: $0.00");
        total.setFont(new Font("SansSerif", Font.BOLD, 24));
        total.setForeground(cafe);
        resumen.add(datos, BorderLayout.NORTH);
        resumen.add(scroll, BorderLayout.CENTER);
        resumen.add(total, BorderLayout.SOUTH);
        panel.add(resumen, BorderLayout.CENTER);

        botonReiniciar = crearBoton("REINICIAR PEDIDOS", true);
        botonReiniciar.setBackground(cafe);
        panel.add(botonReiniciar, BorderLayout.SOUTH);
        return panel;
    }

    // Métodos de presentación: reutilizan colores, fuentes y bordes.
    private JPanel crearSeccion(String titulo, String ayuda) {
        JPanel panel = new JPanel(new BorderLayout(12, 16));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borde),
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));
        JPanel cabecera = new JPanel(new BorderLayout(8, 0));
        cabecera.setOpaque(false);
        JLabel nombre = new JLabel(titulo);
        nombre.setForeground(naranja);
        nombre.setFont(new Font("SansSerif", Font.BOLD, 11));
        JLabel detalle = new JLabel(ayuda);
        detalle.setForeground(gris);
        detalle.setFont(new Font("SansSerif", Font.PLAIN, 10));
        cabecera.add(nombre, BorderLayout.WEST);
        cabecera.add(detalle, BorderLayout.EAST);
        panel.add(cabecera, BorderLayout.NORTH);
        return panel;
    }

    private JButton crearBoton(String texto, boolean destacado) {
        JButton boton = new JButton(texto);
        // Quita el relleno con degradado del botón predeterminado.
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 11));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borde),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        if (destacado) {
            boton.setBackground(naranja);
            boton.setForeground(Color.WHITE);
        } else {
            boton.setBackground(Color.WHITE);
            boton.setForeground(cafe);
        }
        boton.addActionListener(this);
        return boton;
    }

    private JButton crearTarjeta(String titulo, String detalle, String precio) {
        // HTML solo distribuye el texto en varias líneas dentro del botón.
        // Los totales reales siempre se consultan al modelo.
        String texto = "<html><center><font color='#C3683D'><b>"
            + titulo + "</b></font><br><br>" + detalle
            + "<br><br><font color='#C3683D'>" + precio
            + "</font></center></html>";
        return crearBoton(texto, false);
    }

    private void pintarSeleccion(JButton[] botones, int seleccion) {
        for (int i = 0; i < botones.length; i++) {
            if (i == seleccion) {
                botones[i].setBackground(suave);
                botones[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(naranja, 2),
                    BorderFactory.createEmptyBorder(9, 11, 9, 11)
                ));
            } else {
                botones[i].setBackground(Color.WHITE);
                botones[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borde),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Detectamos si se eligió una tarjeta de producto.
        for (int i = 0; i < productos.length; i++) {
            if (evento.getSource() == productos[i]) {
                productoElegido = clavesProductos[i];
                pintarSeleccion(productos, i);
                estado.setText("Producto elegido: " + productoElegido);
                return;
            }
        }
        // Detectamos si se eligió una tarjeta de ingrediente.
        for (int i = 0; i < ingredientes.length; i++) {
            if (evento.getSource() == ingredientes[i]) {
                ingredienteElegido = clavesIngredientes[i];
                pintarSeleccion(ingredientes, i);
                estado.setText("Ingrediente elegido: " + ingredienteElegido);
                return;
            }
        }

        try {
            if (evento.getSource() == botonCrear) {
                Pedido nuevo = controlador.crearPedido(productoElegido);
                actualizarLista(controlador.getPedidos().size() - 1);
                estado.setText("Se creó el pedido " + nuevo.id());
            } else if (evento.getSource() == botonAgregar) {
                String id = idSeleccionado();
                if (id == null) {
                    throw new IllegalArgumentException("Primero selecciona un pedido.");
                }
                if (ingredienteElegido == null) {
                    throw new IllegalArgumentException("Primero selecciona un ingrediente.");
                }
                int posicion = lista.getSelectedIndex();
                controlador.agregarIngredientes(id, ingredienteElegido);
                actualizarLista(posicion);
                estado.setText("Ingrediente agregado. Puedes repetirlo pulsando otra vez.");
            } else if (evento.getSource() == botonLimpiar) {
                ingredienteElegido = null;
                pintarSeleccion(ingredientes, -1);
                estado.setText("Selección de ingrediente limpia; el pedido no cambia.");
            } else if (evento.getSource() == botonReiniciar) {
                controlador.reiniciarPedidos();
                actualizarLista(-1);
                ingredienteElegido = null;
                pintarSeleccion(ingredientes, -1);
                estado.setText("Pedidos reiniciados. Los prototipos se conservan.");
            }
        } catch (IllegalArgumentException error) {
            // Las reglas siguen en el servicio; aquí solo mostramos el error.
            JOptionPane.showMessageDialog(this, error.getMessage(),
                "Revisa la operación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarLista(int seleccion) {
        actualizandoLista = true;
        modeloLista.clear();
        for (Pedido pedido : controlador.getPedidos()) {
            modeloLista.addElement("<html><b>" + pedido.id() + " · "
                + pedido.tipo() + "</b><br>" + pedido.descripcion()
                + "<br><b>$" + pedido.precio().toPlainString() + "</b></html>");
        }
        lista.setSelectedIndex(seleccion);
        cantidad.setText(modeloLista.size() + " pedidos creados");
        actualizandoLista = false;
        mostrarDetalle();
    }

    private String idSeleccionado() {
        int posicion = lista.getSelectedIndex();
        ArrayList<Pedido> pedidos = controlador.getPedidos();
        if (posicion < 0 || posicion >= pedidos.size()) {
            return null;
        }
        return pedidos.get(posicion).id();
    }

    // Este evento ocurre al seleccionar una fila de la lista.
    @Override
    public void valueChanged(ListSelectionEvent evento) {
        if (!actualizandoLista && !evento.getValueIsAdjusting()) {
            mostrarDetalle();
        }
    }

    private void mostrarDetalle() {
        String id = idSeleccionado();
        if (id == null) {
            idDetalle.setText("PEDIDO: sin seleccionar");
            tipoDetalle.setText("PRODUCTO: —");
            descripcion.setText("Crea o selecciona un pedido para ver su detalle.");
            total.setText("TOTAL: $0.00");
            return;
        }
        Pedido pedido = controlador.consultarPedido(id);
        idDetalle.setText("PEDIDO: " + pedido.id());
        tipoDetalle.setText("PRODUCTO: " + pedido.tipo());
        descripcion.setText(pedido.descripcion());
        descripcion.setCaretPosition(0);
        total.setText("TOTAL: $" + pedido.precio().toPlainString());
    }
}
