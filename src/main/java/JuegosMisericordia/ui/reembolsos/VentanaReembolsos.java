package JuegosMisericordia.ui.reembolsos;


import JuegosMisericordia.model.DetalleVenta;
import JuegosMisericordia.model.Venta;
import JuegosMisericordia.services.GestorReembolsos;
import JuegosMisericordia.ui.PanelVendedor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.DriverManager.getConnection;

public class VentanaReembolsos extends javax.swing.JFrame {

    private final GestorReembolsos gestorReembolsos = new GestorReembolsos();
    private DetalleVenta detalleActual;
    String loggedVendedor;

    public VentanaReembolsos(String loggedVendedor1) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        loggedVendedor = loggedVendedor1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        facturaTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonReembolsar = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 720));
        jPanel1.setLayout(null);

        facturaTextArea.setColumns(20);
        facturaTextArea.setFont(new java.awt.Font("Monospaced", 1, 15)); // NOI18N
        facturaTextArea.setRows(5);
        facturaTextArea.setText("**************************************************\n****************COPIA DE LA FACTURA***************\n**************************************************");
        jScrollPane2.setViewportView(facturaTextArea);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(20, 110, 460, 550);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Número de factura:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 70, 170, 30);

        jPanel1.add(jTextField1);
        jTextField1.setBounds(200, 70, 280, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Reembolsos");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(170, 20, 140, 32);

        jButtonReembolsar.setText("Reembolso realizado");
        jPanel1.add(jButtonReembolsar);
        jButtonReembolsar.setBounds(90, 680, 140, 25);

        jButtonConsultar.setText("Consultar Factura");
        jButtonConsultar.addActionListener(this::consultarFacturaActionPerformed);

        jButtonReembolsar.setText("Reembolsar");
        jButtonReembolsar.addActionListener(this::reembolsarActionPerformed);
        jButtonReembolsar.setEnabled(false); // Deshabilitado hasta que se consulte

        jPanel1.add(jButtonConsultar);
        jButtonConsultar.setBounds(270, 680, 140, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>


    

    private void mostrarFacturaCompleta(int numeroVenta) {
        Venta venta = gestorReembolsos.consultarVenta(numeroVenta);
        if (venta == null) {
            facturaTextArea.setText("No se encontró la venta #" + numeroVenta);
            return;
        }

        List<DetalleVenta> detalles = gestorReembolsos.obtenerDetallesVenta(numeroVenta);

        StringBuilder factura = new StringBuilder();
        factura.append("**************************************************\n")
                .append("************** FACTURA COMPLETA ***************\n")
                .append("**************************************************\n\n")
                .append("Número de Factura: ").append(venta.getNumeroVenta()).append("\n")
                .append("Fecha: ").append(venta.getFechaHora()).append("\n")
                .append("Vendedor: ").append(venta.getVendedor().getUsername()).append("\n")
                .append("Estado Reembolso: ").append(venta.getReembolsado()).append("\n\n")
                .append("---------- PRODUCTOS ----------\n");

        for (DetalleVenta detalle : detalles) {
            factura.append("Código: ").append(detalle.getProducto().getCodigo()).append("\n")
                    .append("Nombre: ").append(detalle.getProducto().getNombre()).append("\n")
                    .append("Cantidad: ").append(detalle.getCantidad()).append("\n")
                    .append("Precio Unitario: ").append(String.format("%,.2f", detalle.getPrecioUnitario())).append("$\n")
                    .append("Subtotal: ").append(String.format("%,.2f", detalle.getSubtotal())).append("$\n")
                    .append("---------------------------------------\n");
        }

        factura.append("\nTOTAL FACTURA: ").append(String.format("%,.2f", venta.getMontoTotal())).append("$\n")
                .append("**************************************************");

        facturaTextArea.setText(factura.toString());
    }

    private void reembolsarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int numeroVenta = Integer.parseInt(jTextField1.getText());
            Venta venta = gestorReembolsos.consultarVenta(numeroVenta);

            if (venta == null) {
                JOptionPane.showMessageDialog(this, "Venta no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ("SI".equals(venta.getReembolsado())) {
                JOptionPane.showMessageDialog(this, "Esta venta ya fue reembolsada",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de reembolsar la venta completa #" + numeroVenta + "?",
                    "Confirmar Reembolso", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = gestorReembolsos.realizarReembolso(numeroVenta);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Reembolso realizado con éxito",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    mostrarFacturaCompleta(numeroVenta); // Actualizar vista
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número de venta inválido",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarFacturaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Long idDetalle = Long.parseLong(jTextField1.getText());
            detalleActual = gestorReembolsos.consultarDetalleVenta(idDetalle);

            if (detalleActual == null) {
                facturaTextArea.setText("No se encontró la factura con ID: " + idDetalle);
                jButtonReembolsar.setEnabled(false);
            } else {
                mostrarFacturaCompleta(idDetalle.intValue());
                jButtonReembolsar.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify
    private javax.swing.JTextArea facturaTextArea;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonReembolsar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}

