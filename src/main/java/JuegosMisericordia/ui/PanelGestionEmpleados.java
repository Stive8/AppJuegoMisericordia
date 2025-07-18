package JuegosMisericordia.ui;

import JuegosMisericordia.services.GestorAdministrador;
import JuegosMisericordia.services.GestorVendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class PanelGestionEmpleados extends javax.swing.JFrame {

    DefaultTableModel modelo;
    public PanelGestionEmpleados() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        ImageIcon img = new ImageIcon(getClass().getResource("/icono/iconoBarraJFrame.png"));
        this.setIconImage(img.getImage());
        this.setTitle("SIG - Juegos Misericordia V1.0 - Gestor de Empleados");

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("SALARIO");
        modelo.addColumn("ROL");

        mostrarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        volverButton = new javax.swing.JButton();
        registrarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        editarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmpleados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        volverButton.setText("Volver");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        registrarBtn.setText("Registrar un empleado");
        registrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Borrar un empleado");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        searchBtn.setText("Buscar un empleado");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Seleccione una opción");

        editarBtn.setText("Editar datos");
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(98, 98, 98)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(editarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(212, Short.MAX_VALUE)
                                .addComponent(volverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addComponent(registrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(editarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(volverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))
        );

        jTableEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        jTableEmpleados.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "ID", "NOMBRE", "SALARIO", "ROL"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEmpleados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    public void mostrarDatos() {
        // Limpiar el modelo una sola vez al inicio
        modelo.setRowCount(0);

        // Cargar administradores
        GestorAdministrador gestorAdministrador = new GestorAdministrador();
        gestorAdministrador.subirDatosATabla(modelo);

        // Cargar vendedores
        GestorVendedor gestorVendedor = new GestorVendedor();
        gestorVendedor.subirDatosATabla(modelo);

        // Asignar el modelo actualizado a la tabla
        jTableEmpleados.setModel(modelo);
    }

    public void limpiarDatos(){
        int fila = this.jTableEmpleados.getRowCount();
        for (int i = fila -1; i>=0; i--){
            modelo.removeRow(i);
        }
    }
    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTableEmpleados.getSelectedRow();

        if (selectedRow != -1) {
            int modelRow = jTableEmpleados.convertRowIndexToModel(selectedRow); // Handle sorting/filtering
            Object idValue = modelo.getValueAt(modelRow, 0);
            Object nombreValue = modelo.getValueAt(modelRow, 1);
            Object salarioValue = modelo.getValueAt(modelRow, 2);
            Object rolValue = modelo.getValueAt(modelRow, 3);

            String id = idValue.toString();
            String nombre = nombreValue.toString();
            double salario = (double) salarioValue;
            String rol = rolValue.toString();

            FormularioEditarEmpleado formularioEditarEmpleado = new FormularioEditarEmpleado(id, nombre, salario, rol, this);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún empleado", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void registrarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        FormularioAnadirEmpleado form = new FormularioAnadirEmpleado(this);
    }

    private void eliminarBtnActionPerformed(ActionEvent evt) {
        int selectedRow = jTableEmpleados.getSelectedRow();
        GestorAdministrador gestorAdministrador = new GestorAdministrador();
        GestorVendedor gestorVendedor = new GestorVendedor();

        if (selectedRow != -1) {
            int modelRow = jTableEmpleados.convertRowIndexToModel(selectedRow); // Handle sorting/filtering
            Object idValue = modelo.getValueAt(modelRow, 0);// Assuming the ID is in column 0
            Object rolValue = modelo.getValueAt(modelRow, 3);

            String id = idValue.toString();
            String rol = rolValue.toString();

            int confirmacion = JOptionPane.showConfirmDialog(null, ("¿Está seguro de que desea eliminar al empleado de id "+ id + "?"), null, JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                if(rol.equals("ADMINISTRADOR")){
                    gestorAdministrador.deleteAdmin(Long.valueOf(id));
                }
                if(rol.equals("VENDEDOR")){
                    gestorVendedor.deleteSeller(Long.valueOf(id));
                }
            }

            limpiarDatos();
            mostrarDatos();

        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione el empleado a eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        GestorAdministrador gestAdmin = new GestorAdministrador();
        GestorVendedor gestVen = new GestorVendedor();
        String id = JOptionPane.showInputDialog("Ingrese la ID del empleado a buscar");
        if (!gestAdmin.buscarAdmin(Long.valueOf(id))) {
            gestVen.buscarSeller(Long.valueOf(id));
        }
    }

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        PanelAdmin panelAdmin = new PanelAdmin();
        this.dispose();
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelGestionEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelGestionEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelGestionEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelGestionEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelGestionEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton editarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmpleados;
    private javax.swing.JButton registrarBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton volverButton;
    // End of variables declaration                   
}