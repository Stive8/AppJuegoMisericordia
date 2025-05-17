package JuegosMisericordia.ui.ventas;

import JuegosMisericordia.model.Empleado;
import JuegosMisericordia.model.Venta;
import JuegosMisericordia.services.GestorClientes;
import JuegosMisericordia.services.GestorProductos;
import JuegosMisericordia.services.GestorVentas;
import JuegosMisericordia.ui.PanelVendedor;
import JuegosMisericordia.model.Cliente;
import JuegosMisericordia.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InterfazVentas extends javax.swing.JFrame {

    private GestorVentas gestorVentas = new GestorVentas();
    private String tipoPago;
    private double valorTotal;
    private double cambio;

    String loggedSeller;

    ArrayList<Integer> cantidadesAVender = new ArrayList<>();
    ArrayList<Producto> productosAVender = new ArrayList<>();

    public InterfazVentas(String loggedSeller1) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        fechaField.setText(formattedDate);

        this.loggedSeller = loggedSeller1;
        sellerField1.setText(loggedSeller);

        int siguienteNumero = gestorVentas.generarSiguienteNumeroVenta();
        numField.setText(String.valueOf(siguienteNumero));

        facturaTextArea.setText("**********************************************\n");
        facturaTextArea.setText(facturaTextArea.getText() + "***************FACTURA DE VENTA***************\n");
        facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n");
        facturaTextArea.setText(facturaTextArea.getText() + "FECHA Y HORA: " + fechaField.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "VENDEDOR: " + sellerField1.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "NUMERO DE FACTURA : " + numField.getText() + "\n\n");
        facturaTextArea.setText(facturaTextArea.getText() + "******************DESCRIPCIÓN*****************\n\n");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        imprimirFacturaBtn = new javax.swing.JButton();
        nuevaVentaBtn = new javax.swing.JButton();
        terminarVentaBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        celularField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        correoField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cedulaField = new javax.swing.JTextField();
        agregarProductoBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        clienteRegistradoBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        fechaField = new javax.swing.JTextField();
        tarjetaCreditoBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cantidadField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        valorCambioLabel = new javax.swing.JLabel();
        efectivoBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        tarjetaDebitoBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        valorTransaccionLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cancelarVentaBtn = new javax.swing.JButton();
        codigoField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        sellerField1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        numField = new javax.swing.JTextField();
        registrarClienteBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        facturaTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel2.setLayout(null);

        imprimirFacturaBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        imprimirFacturaBtn.setText("Imprimir Factura");
        imprimirFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirFacturaBtnActionPerformed(evt);
            }
        });
        jPanel2.add(imprimirFacturaBtn);
        imprimirFacturaBtn.setBounds(180, 590, 140, 30);

        nuevaVentaBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        nuevaVentaBtn.setText("Nueva Venta");
        nuevaVentaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaVentaBtnActionPerformed(evt);
            }
        });
        jPanel2.add(nuevaVentaBtn);
        nuevaVentaBtn.setBounds(340, 590, 130, 30);

        terminarVentaBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        terminarVentaBtn.setText("Finalizar Venta");
        terminarVentaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarVentaBtnActionPerformed(evt);
            }
        });
        jPanel2.add(terminarVentaBtn);
        terminarVentaBtn.setBounds(20, 590, 140, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("--------------------------------------------");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(120, 670, 270, 24);
        jPanel2.add(celularField);
        celularField.setBounds(310, 450, 150, 30);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Cantidad:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(270, 150, 80, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("INFORMACIÓN DE VENTA");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(130, 10, 230, 24);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Celular:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(250, 450, 60, 30);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Correo:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(30, 490, 50, 30);
        jPanel2.add(correoField);
        correoField.setBounds(90, 490, 370, 30);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("¿Cliente ya registrado? Haga click aquí:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(90, 420, 260, 18);
        jPanel2.add(cedulaField);
        cedulaField.setBounds(90, 450, 150, 30);

        agregarProductoBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        agregarProductoBtn.setText("Agregar");
        agregarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoBtnActionPerformed(evt);
            }
        });
        jPanel2.add(agregarProductoBtn);
        agregarProductoBtn.setBounds(170, 190, 140, 28);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cedula:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(30, 450, 60, 30);

        clienteRegistradoBtn.setText("X");
        clienteRegistradoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteRegistradoBtnActionPerformed(evt);
            }
        });
        jPanel2.add(clienteRegistradoBtn);
        clienteRegistradoBtn.setBounds(350, 420, 41, 20);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Fecha y hora:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(30, 40, 90, 30);

        fechaField.setForeground(new java.awt.Color(0, 0, 0));
        fechaField.setEnabled(false);
        jPanel2.add(fechaField);
        fechaField.setBounds(120, 40, 330, 30);

        tarjetaCreditoBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        tarjetaCreditoBtn.setText("Tarjeta Crédito");
        tarjetaCreditoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarjetaCreditoBtnActionPerformed(evt);
            }
        });
        jPanel2.add(tarjetaCreditoBtn);
        tarjetaCreditoBtn.setBounds(340, 310, 130, 28);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("$");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(350, 350, 10, 24);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Código:");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(30, 150, 60, 30);

        cantidadField.setForeground(new java.awt.Color(0, 0, 0));
        cantidadField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadFieldActionPerformed(evt);
            }
        });
        jPanel2.add(cantidadField);
        cantidadField.setBounds(350, 150, 100, 30);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Escaneo de productos");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(140, 120, 200, 24);

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("CAMBIO:");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(140, 350, 90, 24);

        valorCambioLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        valorCambioLabel.setForeground(new java.awt.Color(0, 0, 0));
        valorCambioLabel.setText("0");
        jPanel2.add(valorCambioLabel);
        valorCambioLabel.setBounds(230, 350, 110, 24);

        efectivoBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        efectivoBtn.setText("Efectivo");
        efectivoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectivoBtnActionPerformed(evt);
            }
        });
        jPanel2.add(efectivoBtn);
        efectivoBtn.setBounds(30, 310, 130, 28);

        volverBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        volverBtn.setText("VOLVER");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });
        jPanel2.add(volverBtn);
        volverBtn.setBounds(260, 640, 160, 40);

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("--------------------------------------------");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(120, 560, 270, 24);

        tarjetaDebitoBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        tarjetaDebitoBtn.setText("Tarjeta Debito");
        tarjetaDebitoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarjetaDebitoBtnActionPerformed(evt);
            }
        });
        jPanel2.add(tarjetaDebitoBtn);
        tarjetaDebitoBtn.setBounds(180, 310, 140, 28);

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("VALOR DE LA TRANSACCION:");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(30, 230, 280, 24);

        valorTransaccionLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        valorTransaccionLabel.setForeground(new java.awt.Color(0, 0, 0));
        valorTransaccionLabel.setText("0");
        jPanel2.add(valorTransaccionLabel);
        valorTransaccionLabel.setBounds(310, 230, 110, 24);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("$");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(440, 230, 10, 24);

        cancelarVentaBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        cancelarVentaBtn.setText("CANCELAR VENTA");
        cancelarVentaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarVentaBtnActionPerformed(evt);
            }
        });
        jPanel2.add(cancelarVentaBtn);
        cancelarVentaBtn.setBounds(80, 640, 160, 40);

        codigoField.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(codigoField);
        codigoField.setBounds(100, 150, 150, 30);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("INFORMACIÓN DEL CLIENTE");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(130, 390, 280, 24);

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Empleado:");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(30, 80, 90, 30);

        sellerField1.setForeground(new java.awt.Color(0, 0, 0));
        sellerField1.setEnabled(false);
        jPanel2.add(sellerField1);
        sellerField1.setBounds(100, 80, 120, 30);

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Numero de venta:");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(230, 80, 113, 30);

        numField.setForeground(new java.awt.Color(0, 0, 0));
        numField.setEnabled(false);
        jPanel2.add(numField);
        numField.setBounds(350, 80, 100, 30);

        registrarClienteBtn.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        registrarClienteBtn.setText("Registrar Cliente");
        registrarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarClienteBtnActionPerformed(evt);
            }
        });
        jPanel2.add(registrarClienteBtn);
        registrarClienteBtn.setBounds(180, 530, 140, 28);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("METODO DE PAGO");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(170, 280, 170, 24);

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("--------------------------------------------");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(120, 620, 270, 24);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 490, 700);

        jPanel3.setLayout(null);

        facturaTextArea.setColumns(20);
        facturaTextArea.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        facturaTextArea.setRows(5);
        jScrollPane2.setViewportView(facturaTextArea);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 490, 660);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(490, 0, 510, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void cantidadFieldActionPerformed(ActionEvent evt) {
    }


    private void agregarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        GestorProductos gestor = new GestorProductos();
        Producto producto = gestor.searchProducto(codigoField.getText());
        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (gestor.comprobarDisponibilidad(Integer.parseInt(cantidadField.getText()), codigoField.getText())) {
            productosAVender.add(producto);
            cantidadesAVender.add(Integer.parseInt(cantidadField.getText()));
            facturaTextArea.setText(facturaTextArea.getText() + producto.getNombre() + "\n");
            facturaTextArea.setText(facturaTextArea.getText() + cantidadField.getText() + " " + producto.getValorUnitario() + " " + (producto.getValorUnitario() * Integer.parseInt(cantidadField.getText())) + "\n\n");

            double valorArticulos = (producto.getValorUnitario() * Integer.parseInt(cantidadField.getText()));
            double valorTotal = Double.parseDouble(valorTransaccionLabel.getText());
            valorTotal += valorArticulos;
            valorTransaccionLabel.setText(String.valueOf(valorTotal));
            this.valorTotal = valorTotal;

            codigoField.setText("");
            cantidadField.setText("");

        } else {
            JOptionPane.showMessageDialog(null, "No hay suficientes unidades disponibles", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void imprimirFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            facturaTextArea.print();
        } catch (PrinterException e) {
            throw new RuntimeException(e);
        }
    }

    private void nuevaVentaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        InterfazVentas interfaz = new InterfazVentas(loggedSeller);
        this.dispose();
    }

    private void clienteRegistradoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String cedula = JOptionPane.showInputDialog("Ingrese el número de cédula:");
        GestorClientes gestor = new GestorClientes();
        Cliente cliente = gestor.searchCliente(cedula);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no registrado", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            cedulaField.setText(cliente.getCedula());
            celularField.setText(cliente.getCelular());
            correoField.setText(cliente.getCorreo());

            facturaTextArea.setText(facturaTextArea.getText() + "**************INFORMACION CLIENTE*************\n");
            facturaTextArea.setText(facturaTextArea.getText() + "CEDULA: " + cedulaField.getText() + "\n");
            facturaTextArea.setText(facturaTextArea.getText() + "CELULAR: " + celularField.getText() + "\n");
            facturaTextArea.setText(facturaTextArea.getText() + "CORREO: " + correoField.getText() + "\n");
            facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n");
        }
    }

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {
        PanelVendedor panel = new PanelVendedor(loggedSeller);
        this.dispose();
    }

    private void tarjetaDebitoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String numero = JOptionPane.showInputDialog("Ingrese el número de la tajeta");
        JPasswordField pf = new JPasswordField();
        int passwordApproved = JOptionPane.showConfirmDialog(null, pf, "Ingrese su PIN", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (passwordApproved == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Transaccion Aprobada");
            facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n");
            facturaTextArea.setText(facturaTextArea.getText() + "VALOR ARTICULOS = " + valorTransaccionLabel.getText() + "\n");
            facturaTextArea.setText(facturaTextArea.getText() + "TARJETA DEBITO = " + valorTransaccionLabel.getText() + "\n");
            facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n\n");
        }
        this.tipoPago = "Tarjeta Debito";
    }

    private void terminarVentaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        GestorProductos gestorProductos = new GestorProductos();
        GestorVentas gestorVentas = new GestorVentas();

        // Crear y configurar la venta principal
        Venta venta = new Venta();
        venta.setNumeroVenta(gestorVentas.generarSiguienteNumeroVenta());
        venta.setFechaHora(LocalDateTime.now());

        Empleado vendedor = new Empleado();
        vendedor.setId(Long.valueOf(this.loggedSeller));
        venta.setVendedor(vendedor);

        venta.setTipoPago(this.tipoPago);
        venta.setMontoTotal(this.valorTotal);
        venta.setCambio(this.cambio);

        // Guardar venta con sus detalles
        gestorVentas.guardarVentaConDetalles(venta, productosAVender, cantidadesAVender);

        // Actualizar stock de productos
        for (int i = 0; i < productosAVender.size(); i++) {
            gestorProductos.venderProducto(cantidadesAVender.get(i), productosAVender.get(i));
        }

        JOptionPane.showMessageDialog(null, "Venta registrada exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }


    private void efectivoBtnActionPerformed(java.awt.event.ActionEvent evt) {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la cantidad de efectivo:"));
        valorCambioLabel.setText(String.valueOf(cantidad - Double.parseDouble(valorTransaccionLabel.getText())));
        this.cambio= cantidad - Double.parseDouble(valorTransaccionLabel.getText());
        facturaTextArea.setText(facturaTextArea.getText() + "********************************************\n");
        facturaTextArea.setText(facturaTextArea.getText() + "VALOR ARTICULOS = " + valorTransaccionLabel.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "EFECTIVO = " + cantidad + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "CAMBIO = " + valorCambioLabel.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "********************************************\n\n");
        this.tipoPago = "Efectivo";
    }

    private void tarjetaCreditoBtnActionPerformed(ActionEvent evt) {
        String numero = JOptionPane.showInputDialog("Ingrese el número de la tajeta");
        if (numero == null || (numero != null && ("".equals(numero)))) {
            JOptionPane.showMessageDialog(null, "Transaccion Cancelada", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            String numeroCuotas = JOptionPane.showInputDialog(null, "Ingrese el número de cuotas");
            if (numeroCuotas == null || (numeroCuotas != null && ("".equals(numeroCuotas)))) {
                JOptionPane.showMessageDialog(null, "Transaccion Cancelada", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Transaccion Aprobada");

                facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n");
                facturaTextArea.setText(facturaTextArea.getText() + "VALOR ARTICULOS = " + valorTransaccionLabel.getText() + "\n");
                facturaTextArea.setText(facturaTextArea.getText() + "TARJETA CREDITO = " + valorTransaccionLabel.getText() + "\n");
                facturaTextArea.setText(facturaTextArea.getText() + "CUOTAS = " + numeroCuotas + "\n");
                facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n\n");
            }
        }
        this.tipoPago = "Tarjeta Credito";
    }

    private void registrarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String cedula = cedulaField.getText();
        String celular = celularField.getText();
        String correo = correoField.getText();

        Cliente cliente = new Cliente(cedula, celular, correo);
        GestorClientes gestor = new GestorClientes();
        gestor.addCliente(cliente);

        facturaTextArea.setText(facturaTextArea.getText() + "**************INFORMACION CLIENTE*************\n");
        facturaTextArea.setText(facturaTextArea.getText() + "CEDULA: " + cedulaField.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "CELULAR: " + celularField.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "CORREO: " + correoField.getText() + "\n");
        facturaTextArea.setText(facturaTextArea.getText() + "**********************************************\n");
    }

    private void cancelarVentaBtnActionPerformed(java.awt.event.ActionEvent evt) {
        InterfazVentas interfaz = new InterfazVentas(loggedSeller);
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify
    private javax.swing.JButton agregarProductoBtn;
    private javax.swing.JButton cancelarVentaBtn;
    private javax.swing.JTextField cantidadField;
    private javax.swing.JTextField cedulaField;
    private javax.swing.JTextField celularField;
    private javax.swing.JButton clienteRegistradoBtn;
    private javax.swing.JTextField codigoField;
    private javax.swing.JTextField correoField;
    private javax.swing.JButton efectivoBtn;
    private javax.swing.JTextArea facturaTextArea;
    private javax.swing.JTextField fechaField;
    private javax.swing.JButton imprimirFacturaBtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton nuevaVentaBtn;
    private javax.swing.JTextField numField;
    private javax.swing.JButton registrarClienteBtn;
    private javax.swing.JTextField sellerField1;
    private javax.swing.JButton tarjetaCreditoBtn;
    private javax.swing.JButton tarjetaDebitoBtn;
    private javax.swing.JButton terminarVentaBtn;
    private javax.swing.JLabel valorCambioLabel;
    private javax.swing.JLabel valorTransaccionLabel;
    private javax.swing.JButton volverBtn;
    // End of variables declaration
}

