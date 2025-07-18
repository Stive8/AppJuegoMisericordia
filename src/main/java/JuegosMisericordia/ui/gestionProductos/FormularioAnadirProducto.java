package JuegosMisericordia.ui.gestionProductos;

import JuegosMisericordia.services.GestorProductos;
import JuegosMisericordia.model.Producto;

import javax.swing.*;

/**
 *
 * @author Daniel
 */
public class FormularioAnadirProducto extends javax.swing.JFrame {

    /**
     * Creates new form FormularioAnadirEmpleado
     */

    PanelGestionInventario panelGestionInv;
    public FormularioAnadirProducto(PanelGestionInventario panelGestion) {
        initComponents();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon(getClass().getResource("/icono/iconoBarraJFrame.png"));
        this.setIconImage(img.getImage());
        this.setTitle("SIG - Juegos Misericordia V1.0 - Añadir Producto");
        this.setResizable(false);

        panelGestionInv = panelGestion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        stockField = new javax.swing.JTextField();
        codigoField = new javax.swing.JTextField();
        nombreField = new javax.swing.JTextField();
        valorField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        volverBtn = new javax.swing.JButton();
        anadirBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel2.setLayout(null);
        jPanel2.add(stockField);
        stockField.setBounds(260, 290, 200, 30);
        jPanel2.add(codigoField);
        codigoField.setBounds(260, 110, 200, 30);
        jPanel2.add(nombreField);
        nombreField.setBounds(260, 170, 200, 30);
        jPanel2.add(valorField);
        valorField.setBounds(260, 230, 200, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Codigo");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(260, 90, 110, 19);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Valor Unitario");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(260, 210, 110, 19);

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Registro de productos");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(230, 30, 300, 32);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Stock Inicial");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(260, 270, 110, 19);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nombre");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(260, 150, 110, 19);

        volverBtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });
        jPanel2.add(volverBtn);
        volverBtn.setBounds(540, 430, 120, 30);

        anadirBtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        anadirBtn.setText("Agregar");
        anadirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirBtnActionPerformed(evt);
            }
        });
        jPanel2.add(anadirBtn);
        anadirBtn.setBounds(280, 350, 160, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 700, 500);

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

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void anadirBtnActionPerformed(java.awt.event.ActionEvent evt) {

        GestorProductos gestor = new GestorProductos();

        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        double valor = Double.parseDouble(valorField.getText());
        int unidadesDisponibles = Integer.parseInt(stockField.getText());

        Producto producto = new Producto(codigo, nombre, valor, unidadesDisponibles, Producto.ESTADO_ACTIVO);

        gestor.addProducto(producto);

        panelGestionInv.limpiarDatos();
        panelGestionInv.mostrarDatos();

        codigoField.setText("");
        nombreField.setText("");
        valorField.setText("");
        stockField.setText("");
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify
    private javax.swing.JButton anadirBtn;
    private javax.swing.JTextField codigoField;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField stockField;
    private javax.swing.JTextField valorField;
    private javax.swing.JButton volverBtn;
    // End of variables declaration
}