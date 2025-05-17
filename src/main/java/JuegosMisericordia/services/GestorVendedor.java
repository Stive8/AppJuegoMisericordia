package JuegosMisericordia.services;

import JuegosMisericordia.model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorVendedor {

    // Configuración de conexión a Oracle (usando las mismas credenciales que GestorProductos)
    private static final String JDBC_URL = GestorBaseDatos.IP_BASE_DATOS;
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void addSeller(Empleado vendedor) {
        String sql = "INSERT INTO EMPLEADO (USERNAME, PASSWORD, SALARIO, ESTADO, ROL) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Validaciones de longitud (solo para username y password)
            if(vendedor.getUsername().length() > 15) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(vendedor.getPassword().length() > 15) {
                JOptionPane.showMessageDialog(null, "Contraseña demasiado larga (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            stmt.setString(1, vendedor.getUsername());
            stmt.setString(2, vendedor.getPassword());
            stmt.setDouble(3, vendedor.getSalario());
            stmt.setString(4, Empleado.ESTADO_ACTIVO);
            stmt.setString(5, Empleado.ROL_VENDEDOR);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        vendedor.setId(generatedKeys.getLong(1));
                    }
                }
                JOptionPane.showMessageDialog(null, "Vendedor registrado exitosamente con ID: " + vendedor.getId(), null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar vendedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void deleteSeller(Long idBorrar) {  // Cambiar parámetro a Long
        String sql = "UPDATE EMPLEADO SET ESTADO = ? WHERE ID = ? AND ESTADO = ? AND ROL = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_INACTIVO);
            stmt.setLong(2, idBorrar);  // Cambiar a setLong
            stmt.setString(3, Empleado.ESTADO_ACTIVO);
            stmt.setString(4, Empleado.ROL_VENDEDOR);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Vendedor eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor no encontrado o ya inactivo", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean buscarSeller(Long id) {  // Cambiar parámetro a Long
        String sql = "SELECT USERNAME, SALARIO FROM EMPLEADO WHERE ID = ? AND ESTADO = ? AND ROL = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);  // Cambiar a setLong
            stmt.setString(2, Empleado.ESTADO_ACTIVO);
            stmt.setString(3, Empleado.ROL_VENDEDOR);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("USERNAME");
                double salario = rs.getDouble("SALARIO");

                JOptionPane.showMessageDialog(null,
                        "Nombre: " + nombre + "\n" +
                                "Salario: " + salario + "\n" +
                                "Rol: Vendedor");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarRegistro(Long idSeleccionada, String nombreNuevo, double salarioNuevo) {
        // Eliminar validación de ID

        if (nombreNuevo.length() > 15) {
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE EMPLEADO SET USERNAME = ?, SALARIO = ? WHERE ID = ? AND ESTADO = ? AND ROL = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreNuevo);
            stmt.setDouble(2, salarioNuevo);
            stmt.setLong(3, idSeleccionada);  // Cambiar a setLong
            stmt.setString(4, Empleado.ESTADO_ACTIVO);
            stmt.setString(5, Empleado.ROL_VENDEDOR);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Vendedor no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public boolean verificarRegistro(String username, String password) {
        String sql = "SELECT COUNT(*) FROM EMPLEADO WHERE USERNAME = ? AND PASSWORD = ? AND ESTADO = ? AND ROL = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, Empleado.ESTADO_ACTIVO);
            stmt.setString(4, Empleado.ROL_VENDEDOR);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void subirDatosATabla(DefaultTableModel modelo) {
        modelo.setRowCount(0);

        String sql = "SELECT ID, USERNAME, SALARIO FROM EMPLEADO WHERE ESTADO = ? AND ROL = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_ACTIVO);
            stmt.setString(2, Empleado.ROL_VENDEDOR);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getLong("ID"),  // Cambiar a getLong
                        rs.getString("USERNAME"),
                        rs.getDouble("SALARIO"),
                        "VENDEDOR"
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Empleado> obtenerTodosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT ID, USERNAME, SALARIO, ESTADO, ROL FROM EMPLEADO WHERE ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Empleado.ESTADO_ACTIVO);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getLong("ID"));  // Cambiar a getLong
                empleado.setUsername(rs.getString("USERNAME"));
                empleado.setSalario(rs.getDouble("SALARIO"));
                empleado.setEstado(rs.getString("ESTADO"));
                empleado.setRol(rs.getString("ROL"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener empleados: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return empleados;
    }
}