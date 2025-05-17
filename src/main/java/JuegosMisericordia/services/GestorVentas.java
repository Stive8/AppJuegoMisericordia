package JuegosMisericordia.services;

import JuegosMisericordia.model.DetalleVenta;
import JuegosMisericordia.model.Empleado;
import JuegosMisericordia.model.Producto;
import JuegosMisericordia.model.Venta;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorVentas {

    // Configuración de conexión a Oracle
    private static final String JDBC_URL = GestorBaseDatos.IP_BASE_DATOS;
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    // Genera el siguiente número de venta
    public int generarSiguienteNumeroVenta() {
        String sql = "SELECT MAX(NUMERO_VENTA) FROM VENTA";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int ultimo = rs.getInt(1);
                if (rs.wasNull()) {
                    return 1;
                }
                return ultimo + 1;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar número de venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    private void guardarVenta(Connection conn, Venta venta) throws SQLException {

        String empleadoId = obtenerIdEmpleadoPorUsername(String.valueOf(venta.getVendedor().getId()));

        String sql = "INSERT INTO VENTA (NUMERO_VENTA, FECHA_HORA, VENDEDOR_ID, TIPO_PAGO, MONTO_TOTAL, CAMBIO) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venta.getNumeroVenta());
            stmt.setTimestamp(2, Timestamp.valueOf(venta.getFechaHora()));
            stmt.setString(3, empleadoId); // Usamos el ID obtenido
            stmt.setString(4, venta.getTipoPago());
            stmt.setDouble(5, venta.getMontoTotal());
            stmt.setDouble(6, venta.getCambio());

            if (stmt.executeUpdate() == 0) {
                throw new SQLException("No se pudo guardar la venta principal");
            }
        }
    }



    private String obtenerIdEmpleadoPorUsername(String username) {
        String sql = "SELECT ID FROM EMPLEADO WHERE USERNAME = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, Empleado.ESTADO_ACTIVO);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("ID");
            }
            return null;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void guardarVentaConDetalles(Venta venta, List<Producto> productos, List<Integer> cantidades) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Iniciar transacción

            // 1. Guardar la venta principal
            guardarVenta(conn, venta);

            // 2. Guardar los detalles de venta
            guardarDetallesVenta(conn, venta.getNumeroVenta(), productos, cantidades);

            conn.commit();
            JOptionPane.showMessageDialog(null, "Venta y detalles guardados correctamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Error al guardar venta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void guardarDetallesVenta(Connection conn, int numeroVenta,
                                      List<Producto> productos, List<Integer> cantidades) throws SQLException {
        String sql = "INSERT INTO DETALLE_VENTA (NUMERO_VENTA, CODIGO_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, SUBTOTAL) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                int cantidad = cantidades.get(i);
                double precioUnitario = producto.getValorUnitario();
                double subtotal = cantidad * precioUnitario;

                stmt.setInt(1, numeroVenta);
                stmt.setString(2, producto.getCodigo());
                stmt.setInt(3, cantidad);
                stmt.setDouble(4, precioUnitario);
                stmt.setDouble(5, subtotal);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public List<DetalleVenta> obtenerDetallesPorVenta(int numeroVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT d.*, p.NOMBRE, p.VALORUNITARIO " +
                "FROM DETALLE_VENTA d " +
                "JOIN PRODUCTO p ON d.CODIGO_PRODUCTO = p.CODIGO " +
                "WHERE d.NUMERO_VENTA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroVenta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setId(rs.getLong("ID_DETALLE"));

                Venta venta = new Venta();
                venta.setNumeroVenta(rs.getInt("NUMERO_VENTA"));
                detalle.setVenta(venta);

                Producto producto = new Producto();
                producto.setCodigo(rs.getString("CODIGO_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setValorUnitario(rs.getDouble("VALORUNITARIO"));
                detalle.setProducto(producto);

                detalle.setCantidad(rs.getInt("CANTIDAD"));
                detalle.setPrecioUnitario(rs.getDouble("PRECIO_UNITARIO"));
                detalle.setSubtotal(rs.getDouble("SUBTOTAL"));

                detalles.add(detalle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalles: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return detalles;
    }

    public List<Object[]> obtenerProductosMasVendidos(int limite) {
        List<Object[]> resultados = new ArrayList<>();
        String sql = "SELECT p.CODIGO, p.NOMBRE, SUM(d.CANTIDAD) as total_vendido " +
                "FROM DETALLE_VENTA d " +
                "JOIN PRODUCTO p ON d.CODIGO_PRODUCTO = p.CODIGO " +
                "GROUP BY p.CODIGO, p.NOMBRE " +
                "ORDER BY total_vendido DESC " +
                "FETCH FIRST ? ROWS ONLY";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limite);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("CODIGO");
                fila[1] = rs.getString("NOMBRE");
                fila[2] = rs.getInt("total_vendido");
                resultados.add(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener productos más vendidos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return resultados;
    }

    public List<Venta> obtenerVentasPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT v.NUMERO_VENTA, v.FECHA_HORA, v.VENDEDOR_ID, v.TIPO_PAGO, v.MONTO_TOTAL, v.CAMBIO, e.USERNAME " +
                "FROM VENTA v " +
                "JOIN EMPLEADO e ON v.VENDEDOR_ID = e.ID " +
                "WHERE v.FECHA_HORA >= ? AND v.FECHA_HORA <= ? " +
                "ORDER BY v.FECHA_HORA DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Convertir LocalDate a Timestamp (incluye hora 00:00:00)
            stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio.atStartOfDay()));
            // Fecha fin incluye todo el día (hasta 23:59:59)
            stmt.setTimestamp(2, Timestamp.valueOf(fechaFin.atTime(23, 59, 59)));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setNumeroVenta(rs.getInt("NUMERO_VENTA"));
                venta.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                venta.setTipoPago(rs.getString("TIPO_PAGO"));
                venta.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                venta.setCambio(rs.getDouble("CAMBIO"));

                Empleado vendedor = new Empleado();
                vendedor.setId(Long.valueOf(rs.getString("VENDEDOR_ID")));
                vendedor.setUsername(rs.getString("USERNAME"));
                venta.setVendedor(vendedor);

                ventas.add(venta);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ventas por fecha: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return ventas;
    }

}
