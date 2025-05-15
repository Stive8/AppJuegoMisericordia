package JuegosMisericordia.ui;

import JuegosMisericordia.services.GestorAdministrador;
import JuegosMisericordia.services.GestorVendedor;

/**
 * @author Daniel
 */
public class FormularioEditarEmpleado extends javax.swing.JFrame {
    String rolElegido = "";
    String idSeleccionada = "";
    PanelGestionEmpleados panelGestion;

    public FormularioEditarEmpleado(String id, String nombre, double salario, String rol, PanelGestionEmpleados panel) {
        initComponents();

        idField.setText(id);
        nombreField.setText(nombre);
        salarioField.setText(String.valueOf(salario));
        rolElegido = rol;

        this.setVisible(true);
        this.setLocationRelativeTo(null);

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
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
        jLabel1.setText("Edición de datos");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(200, 20, 200, 40);
        jPanel1.add(salarioField);
        salarioField.setBounds(220, 230, 160, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(220, 150, 60, 15);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("ID");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(220, 90, 60, 19);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Salario");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(220, 210, 60, 15);
        jPanel1.add(idField);
        idField.setBounds(220, 110, 160, 30);
        jPanel1.add(nombreField);
        nombreField.setBounds(220, 170, 160, 30);

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
        String id = idField.getText();
        String nombre = nombreField.getText();
        double salario = Double.parseDouble(salarioField.getText());

        if(rolElegido.equals("ADMINISTRADOR")){
            GestorAdministrador gestorAdmin = new GestorAdministrador();
            gestorAdmin.editarRegistro(idSeleccionada, id, nombre, salario);
        } else if (rolElegido.equals("VENDEDOR")) {
            GestorVendedor gestorVendedor = new GestorVendedor();
            gestorVendedor.editarRegistro(idSeleccionada, id, nombre, salario);
        }

        panelGestion.limpiarDatos();
        panelGestion.mostrarDatos();
        this.dispose();
    }

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify                     
    private javax.swing.JButton editarBtn;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField salarioField;
    private javax.swing.JButton volverBtn;
    // End of variables declaration                   
}
