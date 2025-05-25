package JuegosMisericordia.services;

import JuegosMisericordia.model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAdministrador {
    // Configuración de conexión a Oracle

    private static final String JDBC_URL = GestorBaseDatos.IP_BASE_DATOS;
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void addAdmin(Empleado administrador) {
        // Validar si el username ya existe
        if (usernameExists(administrador.getUsername())) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario '" + administrador.getUsername() + "' ya está en uso",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String insertSql = "INSERT INTO EMPLEADO (USERNAME, PASSWORD, SALARIO, ESTADO, ROL) VALUES (?, ?, ?, ?, ?)";
        String selectSql = "SELECT ID FROM EMPLEADO WHERE USERNAME = ?";

        try (Connection conn = getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            // Validaciones de longitud (solo para username y password)
            if (administrador.getUsername().length() > 15) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario demasiado largo (Máx. 15 caracteres)",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (administrador.getPassword().length() > 15) {
                JOptionPane.showMessageDialog(null, "Contraseña demasiado larga (Máx. 15 caracteres)",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insertar el administrador
            insertStmt.setString(1, administrador.getUsername());
            insertStmt.setString(2, administrador.getPassword());
            insertStmt.setDouble(3, administrador.getSalario());
            insertStmt.setString(4, Empleado.ESTADO_ACTIVO);
            insertStmt.setString(5, Empleado.ROL_ADMINISTRADOR);

            int rowsInserted = insertStmt.executeUpdate();

            if (rowsInserted > 0) {
                // Consultar el ID generado usando el username
                selectStmt.setString(1, administrador.getUsername());
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        Long generatedId = rs.getLong("ID");
                        administrador.setId(generatedId);
                        JOptionPane.showMessageDialog(null, "Administrador registrado exitosamente con ID: " + administrador.getId(),
                                null, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        throw new SQLException("No se pudo obtener el ID del administrador recién insertado");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar administrador: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e); // Cambiar a RuntimeException para mantener consistencia
        }
    }

    // Método auxiliar para verificar si el username ya existe (asegúrate de que esté en GestorAdministrador)
    private boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM EMPLEADO WHERE USERNAME = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar username: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return true; // Asumir que existe en caso de error para evitar inserciones riesgosas
        }
    }

    public void deleteAdmin(Long idBorrar) {  // Cambiar a Long
        String sql = "UPDATE EMPLEADO SET estado = ? WHERE id = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_INACTIVO);
            stmt.setLong(2, idBorrar);  // Cambiar a setLong
            stmt.setString(3, Empleado.ESTADO_ACTIVO);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Administrador eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el administrador o ya estaba inactivo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean verificarRegistro(String username, String password) {
        String sql = "SELECT COUNT(*) FROM EMPLEADO WHERE username = ? AND password = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, Empleado.ESTADO_ACTIVO);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar credenciales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public void editarRegistro(Long idSeleccionada, String nombreNuevo, double salarioNuevo) {
        String sql = "UPDATE EMPLEADO SET USERNAME = ?, SALARIO = ? WHERE id = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Eliminar validación de ID
            if (nombreNuevo.length() > 15) {
                JOptionPane.showMessageDialog(null, "Nombre demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            stmt.setString(1, nombreNuevo);
            stmt.setDouble(2, salarioNuevo);
            stmt.setLong(3, idSeleccionada);  // Cambiar a setLong
            stmt.setString(4, Empleado.ESTADO_ACTIVO);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el administrador o está inactivo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean buscarAdmin(Long id) {  // Cambiar a Long
        String sql = "SELECT username, salario FROM EMPLEADO WHERE id = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);  // Cambiar a setLong
            stmt.setString(2, Empleado.ESTADO_ACTIVO);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("username");
                    double salario = rs.getDouble("salario");
                    JOptionPane.showMessageDialog(null, ("Nombre: " + nombre + "\n Salario: " + salario + "\n Rol: Administrador"));
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public void subirDatosATabla(DefaultTableModel modelo) {
        String sql = "SELECT id, username, salario FROM EMPLEADO WHERE estado = ? AND rol = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_ACTIVO);
            stmt.setString(2, Empleado.ROL_ADMINISTRADOR);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                            rs.getLong("id"),  // Cambiar a getLong
                            rs.getString("username"),
                            rs.getDouble("salario"),
                            "ADMINISTRADOR"
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}