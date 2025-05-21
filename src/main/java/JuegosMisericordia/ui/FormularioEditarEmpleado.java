package JuegosMisericordia.ui;

import JuegosMisericordia.services.GestorAdministrador;
import JuegosMisericordia.services.GestorVendedor;
import JuegosMisericordia.ui.gestionProductos.FormularioAnadirProducto;
import JuegosMisericordia.ui.gestionProductos.PanelGestionInventario;

import javax.swing.*;

/**
 * @author Daniel
 */
public class FormularioEditarEmpleado extends javax.swing.JFrame {
    String rolElegido = "";
    String idSeleccionada = "";
    PanelGestionEmpleados panelGestion;

    public FormularioEditarEmpleado(String id, String nombre, double salario, String rol, PanelGestionEmpleados panel) {
        initComponents();

        nombreField.setText(nombre);
        salarioField.setText(String.valueOf(salario));
        rolElegido = rol;

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon img = new ImageIcon(getClass().getResource("/icono/iconoBarraJFrame.png"));
        this.setIconImage(img.getImage());
        this.setTitle("SIG - Juegos Misericordia V1.0 - Editor de Empleados");

        idSeleccionada = id;
        panelGestion = panel;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        salarioField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        editarBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Editar datos");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(200, 20, 200, 40);
        jPanel1.add(salarioField);
        salarioField.setBounds(220, 200, 160, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(220, 120, 60, 15);

         // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Salario");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(220, 180, 60, 15);
        jPanel1.add(nombreField);
        nombreField.setBounds(220, 140, 160, 30);

        editarBtn.setForeground(new java.awt.Color(0, 0, 0));
        editarBtn.setText("Guardar cambios");
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editarBtn);
        editarBtn.setBounds(230, 280, 140, 40);

        volverBtn.setForeground(new java.awt.Color(0, 0, 0));
        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });
        jPanel1.add(volverBtn);
        volverBtn.setBounds(470, 340, 100, 30);

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

    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String nombre = nombreField.getText();
        double salario = Double.parseDouble(salarioField.getText());

        if(rolElegido.equals("ADMINISTRADOR")){
            GestorAdministrador gestorAdmin = new GestorAdministrador();
            gestorAdmin.editarRegistro(Long.valueOf(idSeleccionada), nombre, salario);
        } else if (rolElegido.equals("VENDEDOR")) {
            GestorVendedor gestorVendedor = new GestorVendedor();
            gestorVendedor.editarRegistro(Long.valueOf(idSeleccionada),nombre, salario);
        }

        panelGestion.limpiarDatos();
        panelGestion.mostrarDatos();
        this.dispose();
    }

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(PanelGestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelGestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelGestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelGestionInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioEditarEmpleado("", "", 2000, "",new PanelGestionEmpleados()).setVisible(true);
            }
        });
    }

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify                     
    private javax.swing.JButton editarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField salarioField;
    private javax.swing.JButton volverBtn;
    // End of variables declaration                   
}
