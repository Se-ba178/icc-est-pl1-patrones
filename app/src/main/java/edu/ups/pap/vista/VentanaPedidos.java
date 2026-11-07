package edu.ups.pap.vista;

import javax.swing.*;

import edu.ups.pap.controller.ControladorPedidos;
import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.TipoProducto;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class VentanaPedidos extends JFrame implements ActionListener {

    private final ControladorPedidos controlador;

    private final JComboBox<String> productos;
    private final JComboBox<String> ingredientes;

    private final JButton selecProducto;
    private final JButton agregar;
    private final JButton reiniciar;

    private final DefaultListModel<String> modeloPedidos;
    private final JList<String> listaPedidos;

    private final JTextArea factura;

    // Guarda los identificadores en el mismo orden que la lista visible.
    private final java.util.ArrayList<String> idsPedidos;

    public VentanaPedidos() {

        controlador = new ControladorPedidos();
        idsPedidos = new java.util.ArrayList<>();

        setTitle("Pedido Lab");
        setSize(900, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productos = new JComboBox<>(new String[] { "Café", "Ensalada", "Pizza" });

        ingredientes = new JComboBox<>();

        selecProducto = new JButton("Seleccionar producto");
        agregar = new JButton("Agregar ingredientes");
        reiniciar = new JButton("Reiniciar pedido");

        // Colores de fondo.
        selecProducto.setBackground(new Color(215, 200, 180));
        agregar.setBackground(new Color(215, 200, 180));
        reiniciar.setBackground(new Color(215, 200, 180));

        // Colores de las letras.
        selecProducto.setForeground(new Color(35, 55, 40));
        agregar.setForeground(new Color(35, 55, 40));
        reiniciar.setForeground(new Color(55, 45, 35));

        modeloPedidos = new DefaultListModel<>();
        listaPedidos = new JList<>(modeloPedidos);
        listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        factura = new JTextArea();
        factura.setEditable(false);
        factura.setLineWrap(true);
        factura.setWrapStyleWord(true);

        construirVentana();

        productos.addActionListener(this);
        selecProducto.addActionListener(this);
        agregar.addActionListener(this);
        reiniciar.addActionListener(this);

        listaPedidos.addListSelectionListener(evento -> {
            if (!evento.getValueIsAdjusting()) {
                actualizarIngredientes();
                actualizarFactura();
            }
        });

        actualizarIngredientes();
        actualizarFactura();
    }

    private void construirVentana() {
        // Panel principal donde ira tanto la izquiera como la derecha
        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));//crea bordes para q no choquen lso paneles
        principal.setBackground(new Color(232, 240, 234));

        // el titulo de nuestra ventana general
        JLabel titulo = new JLabel("VENTANA PARA PEDIDOS ");
        titulo.setFont(new Font("Segoe UI", Font.ITALIC, 28));
        titulo.setForeground(new Color(0, 0, 0));

        // un panel dnde abraotrosdos paneles que mostraran lso pedidos ,añadibles etc
        JPanel contenido = new JPanel(new GridLayout(1, 2, 20, 0));
        contenido.setOpaque(false);

        // Columna izquierda.
        JPanel izquierda = new JPanel(new GridLayout(2, 1, 0, 20));
        izquierda.setOpaque(true);

        JPanel panelProducto = crearPanel("PRODUCTO");
        panelProducto.add(new JLabel("Selecciona el producto que deseas copiar:"));
        panelProducto.setFont(new Font("Segoe UI", Font.ITALIC, 23));
        panelProducto.add(productos);
        panelProducto.add(selecProducto);

        JPanel panelIngredientes = crearPanel("INGREDIENTES AÑADIBLES");
        panelIngredientes.add(new JLabel("Selecciona un pedido de la lista:"));
        panelIngredientes.setFont(new Font("Segoe UI", Font.ITALIC, 23));
        panelIngredientes.add(ingredientes);
        panelIngredientes.add(agregar);
        panelIngredientes.add(reiniciar);

        izquierda.add(panelProducto);// SE LE AÑADE AL PAEL PRINCIPAL CON UN GRIDLAYAUT DE UNA COLUMNA DOS FILAS
        izquierda.add(panelIngredientes);

        // Columna derecha.
        JPanel derecha = new JPanel(new GridLayout(2, 1, 0, 20));
        derecha.setOpaque(false);

        // PANEL PARA VER LSO PEDIDOS
        JPanel panelDetalles = crearPanel("DETALLES DEL PEDIDO");
        panelDetalles.setLayout(new BorderLayout());
        panelDetalles.add(new JScrollPane(listaPedidos), BorderLayout.CENTER);
        panelDetalles.setFont(new Font("Segoe UI", Font.ITALIC, 23));

        // PNEL PARA VER LOS DETALLES DE LA FACTURA
        JPanel panelFactura = crearPanel("TOTAL A PAGAR Y RESUMEN DE PEDIDOS");
        panelFactura.setFont(new Font("Segoe UI", Font.ITALIC, 23));
        panelFactura.setLayout(new BorderLayout());
        panelFactura.add(new JScrollPane(factura), BorderLayout.CENTER);

        derecha.add(panelDetalles);
        derecha.add(panelFactura);

        // SE LE AÑADE AMBOS PANELES IZQUIERA¡DA Y DERECHA AL PANEL PRINCIPAL
        contenido.add(izquierda);
        contenido.add(derecha);

        // sE LE AÑADE AL LABEL PRRINCIPAL EL TITULO ENLA PARTE SUPERIOR Y EL PANEL EN
        // EL CENTRO
        principal.add(titulo, BorderLayout.NORTH);
        principal.add(contenido, BorderLayout.CENTER);

        setContentPane(principal);// SE MUESTRA EN EL JFRAME
    }

    private JPanel crearPanel(String titulo) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.setBackground(new Color(255, 245, 200));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(166, 36, 36)), titulo));
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            if (evento.getSource() == selecProducto) {
                crearPedido();

            } else if (evento.getSource() == agregar) {
                agregarIngrediente();

            } else if (evento.getSource() == reiniciar) {
                controlador.reiniciarPedidos();
                actualizarLista(null);

            } else if (evento.getSource() == productos) {
                selecProducto.setText("Duplicar Producto");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),"Revisa tu selección",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void crearPedido() {
        // Las claves deben coincidir con RegistroPrototipos.
        String[] claves = { "cafe", "ensalada", "pizza" };

        String clave = claves[productos.getSelectedIndex()];
        Pedido nuevo = controlador.crearPedido(clave);

        actualizarLista(nuevo.id());
    }

    private void agregarIngrediente() {
        String id = obtenerIdSeleccionado();

        // Verifica que exista un pedido seleccionado.
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido.");
            return;
        }

        int indice = ingredientes.getSelectedIndex();

        // Verifica que exista un ingrediente seleccionado.
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un ingrediente.");
            return;
        }

        Pedido pedido = controlador.consultarPedido(id);
        String clave;

        if (pedido.tipo() == TipoProducto.CAFE) {
            if (indice == 0) {
                clave = "azucar";
            } else {
                clave = "crema";
            }

        } else if (pedido.tipo() == TipoProducto.ENSALADA) {
            if (indice == 0) {
                clave = "tomate";
            } else {
                clave = "pollo";
            }

        } else {
            if (indice == 0) {
                clave = "queso";
            } else {
                clave = "pepperoni";
            }
        }

        controlador.agregarIngredientes(id, clave);
        actualizarLista(id);
    }

    private String obtenerIdSeleccionado() {
        int indice = listaPedidos.getSelectedIndex();

        if (indice == -1) {
            return null;
        }

        return idsPedidos.get(indice);
    }

    private void actualizarLista(String idSeleccionado) {
        listaPedidos.clearSelection();
        modeloPedidos.clear();
        idsPedidos.clear();

        int indiceSeleccionado = -1;

        for (Pedido pedido : controlador.getPedidos()) {
            idsPedidos.add(pedido.id());

            modeloPedidos.addElement(pedido.id() + " | " + pedido.descripcion() + " | $" + pedido.precio().toPlainString());

            if (pedido.id().equals(idSeleccionado)) {
                indiceSeleccionado = idsPedidos.size() - 1;
            }
        }
        if (indiceSeleccionado != -1) {
            listaPedidos.setSelectedIndex(indiceSeleccionado);
        }

        actualizarIngredientes();
        actualizarFactura();
    }

    private void actualizarIngredientes() {
        ingredientes.removeAllItems();

        String id = obtenerIdSeleccionado();
        boolean haySeleccion = id != null;

        ingredientes.setEnabled(haySeleccion);
        agregar.setEnabled(haySeleccion);

        if (!haySeleccion) {
            return;
        }

        Pedido pedido = controlador.consultarPedido(id);

        if (pedido.tipo() == TipoProducto.CAFE) {
            ingredientes.addItem("Azúcar +$0.50");
            ingredientes.addItem("Crema +$1.20");

        } else if (pedido.tipo() == TipoProducto.ENSALADA) {
            ingredientes.addItem("Tomate +$1.10");
            ingredientes.addItem("Pollo +$1.50");

        } else if (pedido.tipo() == TipoProducto.PIZZA) {
            ingredientes.addItem("Queso +$1.00");
            ingredientes.addItem("Pepperoni +$1.10");
        }
    }

    private void actualizarFactura() {
        String texto = "";
        String id = obtenerIdSeleccionado();

        if (id != null) {
            Pedido pedido = controlador.consultarPedido(id);

            texto = "PEDIDO SELECCIONADO\n\n";
            texto += "ID: " + pedido.id() + "\n";
            texto += "Producto: " + pedido.descripcion() + "\n";
            texto += "Precio: $" + pedido.precio() + "\n\n";

        } else {
            texto = "Selecciona un pedido para ver su detalle.\n\n";
        }

        // Suma el precio de todos los pedidos.
        BigDecimal total = BigDecimal.ZERO;

        for (Pedido pedido : controlador.getPedidos()) {
            total = total.add(pedido.precio());
        }

        texto += "Cantidad de pedidos: " + idsPedidos.size() + "\n";
        texto += "TOTAL A PAGAR: $" + total;

        // Muestra el texto en el área de la factura.
        factura.setText(texto);
        factura.setCaretPosition(0);
    }

}