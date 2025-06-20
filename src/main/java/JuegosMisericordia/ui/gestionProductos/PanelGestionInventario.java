package JuegosMisericordia.ui.gestionProductos;

import JuegosMisericordia.ui.PanelAdmin;
import JuegosMisericordia.services.GestorProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

/**
 *
 * @author Daniel
 */
public class PanelGestionInventario extends javax.swing.JFrame {

    /**
     * Creates new form PanelGestionInventario
     */

    DefaultTableModel modelo;
    public PanelGestionInventario() {
        initComponents();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon img = new ImageIcon(getClass().getResource("/icono/iconoBarraJFrame.png"));
        this.setIconImage(img.getImage());
        this.setTitle("SIG - Juegos Misericordia V1.0 - Gestor de Inventario");

        modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PRECIO UNITARIO");
        modelo.addColumn("UNIDADES DISPONIBLES");

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        volverBtn = new javax.swing.JButton();
        consultarBtn = new javax.swing.JButton();
        anadirBtn = new javax.swing.JButton();
        editarBtn = new javax.swing.JButton();
        abastecerBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(650, 600));
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 600));

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 650, 600);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(350, 600));
        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Seleccione una opción");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(70, 80, 320, 40);

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });
        jPanel3.add(volverBtn);
        volverBtn.setBounds(230, 530, 100, 30);

        consultarBtn.setText("Buscar producto");
        consultarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarBtnActionPerformed(evt);
            }
        });
        jPanel3.add(consultarBtn);
        consultarBtn.setBounds(100, 430, 160, 40);

        anadirBtn.setText("Añadir producto");
        anadirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirBtnActionPerformed(evt);
            }
        });
        jPanel3.add(anadirBtn);
        anadirBtn.setBounds(100, 150, 160, 40);

        editarBtn.setText("Editar Información");
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });
        jPanel3.add(editarBtn);
        editarBtn.setBounds(100, 220, 160, 40);

        abastecerBtn.setText("Abastecer producto");
        abastecerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abastecerBtnActionPerformed(evt);
            }
        });
        jPanel3.add(abastecerBtn);
        abastecerBtn.setBounds(100, 290, 160, 40);

        eliminarBtn.setText("Eliminar producto");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });
        jPanel3.add(eliminarBtn);
        eliminarBtn.setBounds(100, 360, 160, 40);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(650, 0, 350, 600);

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

    public void mostrarDatos(){
        GestorProductos gestorProductos = new GestorProductos();
        gestorProductos.subirDatosATabla(modelo);

        jTableProductos.setModel(modelo);
    }

    public void limpiarDatos(){
        int fila = this.jTableProductos.getRowCount();
        for (int i = fila -1; i>=0; i--){
            modelo.removeRow(i);
        }
    }

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {
        PanelAdmin panelAdmin = new PanelAdmin();
        this.dispose();
    }

    private void consultarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        GestorProductos gestor = new GestorProductos();
        String codigoABuscar = JOptionPane.showInputDialog("Ingrese el código del producto a buscar");

        gestor.buscarProducto(codigoABuscar);
    }

    private void anadirBtnActionPerformed(java.awt.event.ActionEvent evt) {
        FormularioAnadirProducto formAnadir = new FormularioAnadirProducto(this);
    }

    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTableProductos.getSelectedRow();

        if (selectedRow != -1) {
            int modelRow = jTableProductos.convertRowIndexToModel(selectedRow); // Handle sorting/filtering
            Object codigoValue = modelo.getValueAt(modelRow, 0);
            Object nombreValue = modelo.getValueAt(modelRow, 1);
            Object valorValue = modelo.getValueAt(modelRow, 2);
            Object stockValue = modelo.getValueAt(modelRow, 3);

            String codigo = codigoValue.toString();
            String nombre = nombreValue.toString();
            double valorUnitario = (double) valorValue;
            int unidadesDisponibles = (int) stockValue;

            FormularioEditarProducto formularioEditarProducto = new FormularioEditarProducto(codigo, nombre, valorUnitario, unidadesDisponibles, this);

        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione el producto a editar", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void abastecerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTableProductos.getSelectedRow();

        if (selectedRow != -1) {
            int modelRow = jTableProductos.convertRowIndexToModel(selectedRow); // Handle sorting/filtering
            Object codigoValue = modelo.getValueAt(modelRow, 0);
            String codigoAEditar = codigoValue.toString();
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de unidades a abastecer"));

            GestorProductos gestor = new GestorProductos();
            gestor.abastecerStock(cantidad, codigoAEditar);

            limpiarDatos();
            mostrarDatos();

        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione el producto a abastecer", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarBtnActionPerformed(ActionEvent evt) {
        int selectedRow = jTableProductos.getSelectedRow();
        GestorProductos gestor = new GestorProductos();

        if (selectedRow != -1) {
            int modelRow = jTableProductos.convertRowIndexToModel(selectedRow);
            Object codigoValue = modelo.getValueAt(modelRow, 0);
            Object nombreValue = modelo.getValueAt(modelRow, 1);

            String codigoAEliminar = codigoValue.toString();
            String nombre = nombreValue.toString();

            int confirmacion = JOptionPane.showConfirmDialog(null, ("¿Está seguro de que desea eliminar este producto ?"+"\n Codigo: " + codigoAEliminar + "\n Nombre: " + nombre), null, JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                gestor.eliminarProducto(codigoAEliminar);
            }

            limpiarDatos();
            mostrarDatos();

        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione el producto a eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify
    private javax.swing.JButton abastecerBtn;
    private javax.swing.JButton anadirBtn;
    private javax.swing.JButton consultarBtn;
    private javax.swing.JButton editarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JButton volverBtn;
    // End of variables declaration
}
