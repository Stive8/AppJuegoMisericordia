package JuegosMisericordia.services;

import JuegosMisericordia.model.Empleado;
import JuegosMisericordia.model.Venta;

import javax.swing.*;
import java.sql.*;

public class GestorVentas {

    // Configuración de conexión a Oracle
    private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.1.142:1521:XE";
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

    public void guardarVenta(Venta venta) {
        // Primero obtenemos el ID del empleado basado en el username
        String empleadoId = obtenerIdEmpleadoPorUsername(venta.getVendedor().getId());

        if (empleadoId == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el vendedor", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "INSERT INTO VENTA (NUMERO_VENTA, FECHA_HORA, VENDEDOR_ID, TIPO_PAGO, MONTO_TOTAL, CAMBIO) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, venta.getNumeroVenta());
            stmt.setTimestamp(2, Timestamp.valueOf(venta.getFechaHora()));
            stmt.setString(3, empleadoId); // Usamos el ID obtenido
            stmt.setString(4, venta.getTipoPago());
            stmt.setDouble(5, venta.getMontoTotal());
            stmt.setDouble(6, venta.getCambio());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Venta guardada correctamente", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se guardó la venta", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
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

}
