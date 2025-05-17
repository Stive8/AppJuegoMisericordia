package JuegosMisericordia.services;

import JuegosMisericordia.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class GestorClientes {

    // Configuración de conexión a Oracle (usando las mismas credenciales)
    private static final String JDBC_URL = GestorBaseDatos.IP_BASE_DATOS;
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    // Tamaños máximos (se mantienen para validaciones)
    public static final int TAM_MAX_CEDULA = 10;
    public static final int TAM_MAX_CELULAR = 10;
    public static final int TAM_MAX_CORREO = 40;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void addCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (CEDULA, CELULAR, CORREO) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Validaciones de longitud
            if (cliente.getCedula().length() > TAM_MAX_CEDULA) {
                JOptionPane.showMessageDialog(null,
                        "Cédula demasiado larga (Máx. 10 caracteres)\nVerifique que la información sea correcta",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cliente.getCelular().length() > TAM_MAX_CELULAR) {
                JOptionPane.showMessageDialog(null,
                        "Número de celular demasiado largo (Máx. 10 caracteres)\nVerifique que la información sea correcta",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cliente.getCorreo().length() > TAM_MAX_CORREO) {
                JOptionPane.showMessageDialog(null,
                        "Correo demasiado largo (Máx. 40 caracteres)",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            stmt.setString(1, cliente.getCedula());
            stmt.setString(2, cliente.getCelular());
            stmt.setString(3, cliente.getCorreo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null,
                        "Cliente registrado exitosamente",
                        null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al registrar cliente: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public Cliente searchCliente(String cedulaABuscar) {
        String sql = "SELECT CEDULA, CELULAR, CORREO FROM CLIENTE WHERE CEDULA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedulaABuscar.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getString("CEDULA"),
                        rs.getString("CELULAR"),
                        rs.getString("CORREO")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método adicional para actualizar cliente
    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET CELULAR = ?, CORREO = ? WHERE CEDULA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Validaciones de longitud
            if (cliente.getCelular().length() > TAM_MAX_CELULAR) {
                JOptionPane.showMessageDialog(null,
                        "Número de celular demasiado largo (Máx. 10 caracteres)",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cliente.getCorreo().length() > TAM_MAX_CORREO) {
                JOptionPane.showMessageDialog(null,
                        "Correo demasiado largo (Máx. 40 caracteres)",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            stmt.setString(1, cliente.getCelular());
            stmt.setString(2, cliente.getCorreo());
            stmt.setString(3, cliente.getCedula());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null,
                        "Cliente actualizado exitosamente",
                        null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cliente no encontrado",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método adicional para eliminar cliente
    public void deleteCliente(String cedula) {
        String sql = "DELETE FROM CLIENTE WHERE CEDULA = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cedula);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null,
                        "Cliente eliminado exitosamente",
                        null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cliente no encontrado",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método adicional para listar todos los clientes (útil para JTable)
    public void loadClientesToTable(DefaultTableModel model) {
        model.setRowCount(0); // Limpiar la tabla

        String sql = "SELECT CEDULA, CELULAR, CORREO FROM CLIENTE ORDER BY CEDULA";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("CEDULA"),
                        rs.getString("CELULAR"),
                        rs.getString("CORREO")
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}