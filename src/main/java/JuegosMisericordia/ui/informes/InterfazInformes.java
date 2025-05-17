package JuegosMisericordia.ui.informes;

import JuegosMisericordia.model.Empleado;
import JuegosMisericordia.model.Venta;
import JuegosMisericordia.services.GestorVendedor;
import JuegosMisericordia.services.GestorVentas;
import JuegosMisericordia.ui.PanelAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class InterfazInformes extends JFrame {

    public InterfazInformes() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        volverButton = new JButton();
        productosMasVendidosButton = new JButton();
        deVentasButton = new JButton();
        deEmpleadosButton = new JButton();
        jLabel2 = new JLabel();
        imprimirButton = new JButton();
        jSeparator1 = new JSeparator();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setPreferredSize(new Dimension(1000, 600));
        jPanel1.setLayout(null);

        jLabel1.setFont(new Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("--------------------------------------");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 330, 290, 50);

        volverButton.setText("Volver");
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });
        jPanel1.add(volverButton);
        volverButton.setBounds(50, 530, 90, 30);

        productosMasVendidosButton.setText("Productos más vendidos");
        productosMasVendidosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                productosMasVendidosButtonActionPerformed(evt);
            }
        });
        jPanel1.add(productosMasVendidosButton);
        productosMasVendidosButton.setBounds(50, 170, 270, 40);

        deVentasButton.setText("De ventas");
        deVentasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deVentasButtonActionPerformed(evt);
            }
        });
        jPanel1.add(deVentasButton);
        deVentasButton.setBounds(50, 230, 270, 40);

        deEmpleadosButton.setText("De empleados");
        deEmpleadosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deEmpleadosButtonActionPerformed(evt);
            }
        });
        jPanel1.add(deEmpleadosButton);
        deEmpleadosButton.setBounds(50, 290, 270, 40);

        jLabel2.setFont(new Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Seleccione el tipo de informe");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 100, 330, 50);

        imprimirButton.setText("Imprimir / Guardar");
        imprimirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                imprimirButtonActionPerformed(evt);
            }
        });
        jPanel1.add(imprimirButton);
        imprimirButton.setBounds(50, 390, 270, 40);

        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(370, 0, 10, 600);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(400, 20, 580, 560);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void volverButtonActionPerformed(ActionEvent evt) {
        PanelAdmin panelAdmin = new PanelAdmin();
        panelAdmin.setVisible(true);
        panelAdmin.setLocationRelativeTo(null);
        this.dispose();
    }

    private void productosMasVendidosButtonActionPerformed(ActionEvent evt) {
        GestorVentas gestorVentas = new GestorVentas();

        // Encabezado del informe
        jTextArea1.setText("SISTEMA INTEGRADO DE GESTION - JUEGOS MISERICORDIA \n" +
                "INFORME DE PRODUCTOS MAS VENDIDOS\n" +
                "FECHA: " + LocalDate.now() + "\n" +
                "\n" +
                "---------------------------------------\n" +
                "TOP 10 PRODUCTOS MÁS VENDIDOS\n" +
                "---------------------------------------\n\n");

        // Obtener los 10 productos más vendidos
        List<Object[]> productosMasVendidos = gestorVentas.obtenerProductosMasVendidos(10);

        // Formatear los resultados
        StringBuilder informe = new StringBuilder();
        informe.append(jTextArea1.getText()); // Mantener el encabezado

        if (productosMasVendidos.isEmpty()) {
            informe.append("No se encontraron ventas registradas.");
        } else {
            // Cabecera de la tabla
            informe.append(String.format("%-10s %-40s %-15s\n",
                    "CÓDIGO", "NOMBRE", "TOTAL VENDIDO"));
            informe.append("------------------------------------------------------------\n");

            // Datos de cada producto
            for (Object[] producto : productosMasVendidos) {
                informe.append(String.format("%-10s %-40s %-15d\n",
                        producto[0], producto[1], producto[2]));
            }
        }


        jTextArea1.setText(informe.toString());

        // Agregar al final del método
        jTextArea1.append("\n---------------------------------------\n");
        jTextArea1.append("Total de productos diferentes vendidos: " + productosMasVendidos.size() + "\n");

// Calcular y mostrar el total general
        int totalGeneral = productosMasVendidos.stream()
                .mapToInt(p -> (Integer) p[2])
                .sum();
        jTextArea1.append("Total general de unidades vendidas: " + totalGeneral + "\n");

// Opcional: agregar firma
        jTextArea1.append("\n\nGenerado por: Sistema de Gestión\n");
    }

    private void deEmpleadosButtonActionPerformed(ActionEvent evt) {
        double nomina = 0;
        GestorVendedor gestor = new GestorVendedor();

        // Encabezado Informe
        jTextArea1.setText("SISTEMA INTEGRADO DE GESTION - JUEGOS MISERICORDIA \n" +
                "INFORME DE EMPLEADOS CONTRATADOS\n" +
                "FECHA: " + LocalDate.now() + "\n" +
                "\n" +
                "---------------------------------------" +
                "\n \n"
        );

        // Obtener todos los empleados activos
        List<Empleado> empleados = gestor.obtenerTodosEmpleados();

        // Usar StringBuilder para mejor rendimiento con textos largos
        StringBuilder informe = new StringBuilder(jTextArea1.getText());

        if (empleados.isEmpty()) {
            informe.append("No hay empleados activos registrados.\n");
        } else {
            // Imprimir cada empleado
            for (Empleado empleado : empleados) {
                informe.append("ID: ").append(empleado.getId()).append("\n")
                        .append("NOMBRE: ").append(empleado.getUsername()).append("\n")
                        .append("ROL: ").append(empleado.getRol()).append("\n")
                        .append("SALARIO: ").append(String.format("%,.2f", empleado.getSalario())).append("$\n")
                        .append("---------------------------------------\n\n");

                nomina += empleado.getSalario();
            }
        }

        // Imprimir Nómina
        informe.append("--------------------------------------- \n")
                .append("NOMINA TOTAL DE LA EMPRE SA: \n")
                .append(String.format("%,.2f", nomina)).append("$\n")
                .append("--------------------------------------- \n")
                .append("Total empleados activos: ").append(empleados.size()).append("\n");

        jTextArea1.setText(informe.toString());
    }

    private LocalDate verificarFormatoFechaInicio() {
        while (true) {
            String fechaInicioString = JOptionPane.showInputDialog(null, "Por favor ingrese la fecha de inicio del reporte (YYYY-MM-DD)", "Generación Reporte", JOptionPane.INFORMATION_MESSAGE);
            if (fechaInicioString == null) {
                return null;
            } else {
                try {
                    return LocalDate.parse(fechaInicioString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Formato Inválido en la Fecha, intente de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private LocalDate verificarFormatoFechaFin() {
        while (true) {
            String fechaFinString = JOptionPane.showInputDialog(null, "Por favor ingrese la fecha de fin del reporte (YYYY-MM-DD)", "Generación Reporte", JOptionPane.INFORMATION_MESSAGE);
            if (fechaFinString == null) {
                return null;
            } else {
                try {
                    return LocalDate.parse(fechaFinString);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Formato Inválido en la Fecha, intente de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private void deVentasButtonActionPerformed(ActionEvent evt) {
        // Ingresar las fechas de inicio y fin y validar el formato
        LocalDate fechaInicioValida = verificarFormatoFechaInicio();
        if (fechaInicioValida == null) {
            JOptionPane.showMessageDialog(null, "Generación de informe cancelada", "Cancelación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate fechaFinValida = verificarFormatoFechaFin();
        if (fechaFinValida == null) {
            JOptionPane.showMessageDialog(null, "Generación de informe cancelada", "Cancelación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que fecha fin no sea anterior a fecha inicio
        if (fechaFinValida.isBefore(fechaInicioValida)) {
            JOptionPane.showMessageDialog(null, "La fecha final no puede ser anterior a la fecha inicial",
                    "Error en fechas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GestorVentas gestorVentas = new GestorVentas();
        List<Venta> ventas = gestorVentas.obtenerVentasPorFecha(fechaInicioValida, fechaFinValida);
        double totalVentas = ventas.stream().mapToDouble(Venta::getMontoTotal).sum();

        // Usar StringBuilder para mejor rendimiento con textos largos
        StringBuilder informe = new StringBuilder();

        // Encabezado Informe
        informe.append("SISTEMA INTEGRADO DE GESTION - JUEGOS MISERICORDIA \n")
                .append("INFORME DE VENTAS ENTRE ").append(fechaInicioValida)
                .append(" Y ").append(fechaFinValida).append("\n")
                .append("FECHA GENERACIÓN: ").append(LocalDate.now()).append("\n\n")
                .append("---------------------------------------\n\n");

        if (ventas.isEmpty()) {
            informe.append("No se encontraron ventas en el rango de fechas especificado.\n");
        } else {
            // Imprimir cada venta
            for (Venta venta : ventas) {
                informe.append("NÚMERO DE VENTA: ").append(venta.getNumeroVenta()).append("\n")
                        .append("FECHA Y HORA: ").append(venta.getFechaHora()).append("\n")
                        .append("VENDEDOR: ").append(venta.getVendedor().getUsername()).append("\n")
                        .append("TIPO DE PAGO: ").append(venta.getTipoPago()).append("\n")
                        .append("VALOR: ").append(String.format("%,.2f", venta.getMontoTotal())).append("$\n")
                        .append("---------------------------------------\n\n");
            }
        }

        // Totales
        informe.append("---------------------------------------\n")
                .append("TOTAL INGRESOS ENTRE ").append(fechaInicioValida)
                .append(" Y ").append(fechaFinValida).append(":\n")
                .append(String.format("%,.2f", totalVentas)).append("$\n")
                .append("Total ventas registradas: ").append(ventas.size()).append("\n")
                .append("---------------------------------------\n");

        jTextArea1.setText(informe.toString());
    }

    private void imprimirButtonActionPerformed(ActionEvent evt) {
        try {
            jTextArea1.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InterfazInformes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InterfazInformes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InterfazInformes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(InterfazInformes.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazInformes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JButton deEmpleadosButton;
    private JButton deVentasButton;
    private JButton imprimirButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JTextArea jTextArea1;
    private JButton productosMasVendidosButton;
    private JButton volverButton;
    // End of variables declaration
}