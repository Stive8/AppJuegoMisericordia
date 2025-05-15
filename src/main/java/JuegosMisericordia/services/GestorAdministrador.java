package JuegosMisericordia.services;

import JuegosMisericordia.model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorAdministrador {
    // Configuración de conexión a Oracle
    private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.1.142:1521:XE"; // Cambia según tu configuración
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void addAdmin(Empleado administrador) {
        String sql = "INSERT INTO EMPLEADO (id, username, password, salario, estado, rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Validaciones de longitud (opcional, también podrían hacerse en la BD)
            if (administrador.getId().length() > 8) {
                JOptionPane.showMessageDialog(null, "ID Demasiado larga (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (administrador.getUsername().length() > 15) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (administrador.getPassword().length() > 15) {
                JOptionPane.showMessageDialog(null, "Contraseña demasiado larga (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            stmt.setString(1, administrador.getId());
            stmt.setString(2, administrador.getUsername());
            stmt.setString(3, administrador.getPassword());
            stmt.setDouble(4, administrador.getSalario());
            stmt.setString(5, Empleado.ESTADO_ACTIVO);
            stmt.setString(6, Empleado.ROL_ADMINISTRADOR);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void deleteAdmin(String idBorrar) {
        String sql = "UPDATE EMPLEADO SET estado = ? WHERE id = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_INACTIVO);
            stmt.setString(2, idBorrar);
            stmt.setString(3, Empleado.ESTADO_ACTIVO);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el empleado o ya estaba inactivo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    public void editarRegistro(String idSeleccionada, String idNueva, String nombreNuevo, double salarioNuevo) {
        String sql = "UPDATE EMPLEADO SET id = ?, username = ?, salario = ? WHERE id = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Validaciones de longitud
            if (idNueva.length() > 8) {
                JOptionPane.showMessageDialog(null, "ID demasiado larga (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (nombreNuevo.length() > 15) {
                JOptionPane.showMessageDialog(null, "Nombre demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            stmt.setString(1, idNueva);
            stmt.setString(2, nombreNuevo);
            stmt.setDouble(3, salarioNuevo);
            stmt.setString(4, idSeleccionada);
            stmt.setString(5, Empleado.ESTADO_ACTIVO);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el empleado o está inactivo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean buscarAdmin(String id) {
        String sql = "SELECT username, salario FROM EMPLEADO WHERE id = ? AND estado = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.setString(2, Empleado.ESTADO_ACTIVO);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("username");
                    double salario = rs.getDouble("salario");
                    JOptionPane.showMessageDialog(null, ("Nombre: " + nombre + "\n Salario: " + salario + "\n Rol: Empleado"));
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public void subirDatosATabla(DefaultTableModel modelo) {
        // Limpiar el modelo primero
        modelo.setRowCount(0);

        String sql = "SELECT id, username, salario FROM EMPLEADO WHERE estado = ? AND rol = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_ACTIVO);
            stmt.setString(2, Empleado.ROL_ADMINISTRADOR);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String nombre = rs.getString("username");
                    double salario = rs.getDouble("salario");

                    modelo.addRow(new Object[]{id, nombre, salario, "ADMINISTRADOR"});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}