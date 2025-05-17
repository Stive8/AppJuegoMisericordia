package JuegosMisericordia.services;

import JuegosMisericordia.model.DetalleVenta;
import JuegosMisericordia.model.Empleado;
import JuegosMisericordia.model.Producto;
import JuegosMisericordia.model.Venta;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorReembolsos {
    private static final String JDBC_URL = GestorBaseDatos.IP_BASE_DATOS;
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public List<DetalleVenta> obtenerDetallesPorNumeroVenta(int numeroVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT d.*, p.NOMBRE, p.VALOR_UNITARIO, v.FECHA_HORA, e.USERNAME as VENDEDOR " +
                "FROM DETALLE_VENTA d " +
                "JOIN PRODUCTO p ON d.CODIGO_PRODUCTO = p.CODIGO " +
                "JOIN VENTA v ON d.NUMERO_VENTA = v.NUMERO_VENTA " +
                "JOIN EMPLEADO e ON v.VENDEDOR_ID = e.ID " +
                "WHERE d.NUMERO_VENTA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroVenta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = mapearDetalleVenta(rs);
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalles de venta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return detalles;
    }

    public DetalleVenta consultarDetalleVenta(Long idDetalle) {
        String sql = "SELECT d.*, p.NOMBRE, p.VALOR_UNITARIO, v.FECHA_HORA, e.USERNAME as VENDEDOR " +
                "FROM DETALLE_VENTA d " +
                "JOIN PRODUCTO p ON d.CODIGO_PRODUCTO = p.CODIGO " +
                "JOIN VENTA v ON d.NUMERO_VENTA = v.NUMERO_VENTA " +
                "JOIN EMPLEADO e ON v.VENDEDOR_ID = e.ID " +
                "WHERE d.ID_DETALLE = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idDetalle);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearDetalleVenta(rs);
            }
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar detalle de venta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public boolean realizarReembolso(Long idDetalle) {
        String sql = "UPDATE DETALLE_VENTA SET REEMBOLSADO = 'SI' WHERE ID_DETALLE = ? AND REEMBOLSADO = 'NO'";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idDetalle);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar reembolso: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private DetalleVenta mapearDetalleVenta(ResultSet rs) throws SQLException {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(rs.getLong("ID_DETALLE"));
        detalle.setCantidad(rs.getInt("CANTIDAD"));
        detalle.setPrecioUnitario(rs.getDouble("PRECIO_UNITARIO"));
        detalle.setSubtotal(rs.getDouble("SUBTOTAL"));

        Producto producto = new Producto();
        producto.setCodigo(rs.getString("CODIGO_PRODUCTO"));
        producto.setNombre(rs.getString("NOMBRE"));
        producto.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
        detalle.setProducto(producto);

        Venta venta = new Venta();
        venta.setNumeroVenta(rs.getInt("NUMERO_VENTA"));
        venta.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());

        Empleado vendedor = new Empleado();
        vendedor.setUsername(rs.getString("VENDEDOR"));
        venta.setVendedor(vendedor);

        detalle.setVenta(venta);

        return detalle;
    }

    public Venta consultarVenta(int numeroVenta) {
        String sql = "SELECT v.*, e.USERNAME as VENDEDOR " +
                "FROM VENTA v " +
                "JOIN EMPLEADO e ON v.VENDEDOR_ID = e.ID " +
                "WHERE v.NUMERO_VENTA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroVenta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Venta venta = new Venta();
                venta.setNumeroVenta(rs.getInt("NUMERO_VENTA"));
                venta.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                venta.setTipoPago(rs.getString("TIPO_PAGO"));
                venta.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                venta.setCambio(rs.getDouble("CAMBIO"));
                venta.setReembolsado(rs.getString("REEMBOLSADO"));

                Empleado vendedor = new Empleado();
                vendedor.setUsername(rs.getString("VENDEDOR"));
                venta.setVendedor(vendedor);

                return venta;
            }
            return null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar venta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public boolean realizarReembolso(int numeroVenta) {
        String sql = "UPDATE VENTA SET REEMBOLSADO = 'SI' WHERE NUMERO_VENTA = ? AND REEMBOLSADO = 'NO'";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroVenta);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar reembolso: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<DetalleVenta> obtenerDetallesVenta(int numeroVenta) {
        // Implementación existente (sin el campo reembolsado)
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT d.*, p.NOMBRE, p.VALOR_UNITARIO, v.FECHA_HORA, e.USERNAME as VENDEDOR " +
                "FROM DETALLE_VENTA d " +
                "JOIN PRODUCTO p ON d.CODIGO_PRODUCTO = p.CODIGO " +
                "JOIN VENTA v ON d.NUMERO_VENTA = v.NUMERO_VENTA " +
                "JOIN EMPLEADO e ON v.VENDEDOR_ID = e.ID " +
                "WHERE d.NUMERO_VENTA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroVenta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = mapearDetalleVenta(rs);
                detalles.add(detalle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalles de venta: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return detalles;
    }
}