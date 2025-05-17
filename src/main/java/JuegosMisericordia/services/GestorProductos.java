package JuegosMisericordia.services;

import JuegosMisericordia.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class GestorProductos {

    // Configuración de conexión a Oracle
    private static final String JDBC_URL = GestorBaseDatos.IP_BASE_DATOS;
    private static final String USER = "DAE2024";
    private static final String PASSWORD = "DAE2024";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void addProducto(Producto producto) {
        String sql = "INSERT INTO PRODUCTO (CODIGO, NOMBRE, VALOR_UNITARIO, UNIDADES_DISPONIBLES, ESTADO) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getValorUnitario());
            stmt.setInt(4, producto.getUnidadesDisponibles());
            stmt.setString(5, Producto.ESTADO_ACTIVO);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Producto añadido exitosamente", null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al añadir producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void eliminarProducto(String codigoABorrar) {
        String sql = "UPDATE PRODUCTO SET ESTADO = ? WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Producto.ESTADO_INACTIVO);
            stmt.setString(2, codigoABorrar);
            stmt.setString(3, Producto.ESTADO_ACTIVO);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Producto eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado o ya inactivo", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void subirDatosATabla(DefaultTableModel modelo) {
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar datos

        String sql = "SELECT CODIGO, NOMBRE, VALOR_UNITARIO, UNIDADES_DISPONIBLES FROM PRODUCTO WHERE ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Producto.ESTADO_ACTIVO);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getDouble("VALOR_UNITARIO"),
                        rs.getInt("UNIDADES_DISPONIBLES")
                });
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarProducto(String codigoABuscar) {
        String sql = "SELECT NOMBRE, VALOR_UNITARIO, UNIDADES_DISPONIBLES FROM PRODUCTO WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoABuscar);
            stmt.setString(2, Producto.ESTADO_ACTIVO);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                double valorUnitario = rs.getDouble("VALOR_UNITARIO");
                int UNIDADES_DISPONIBLES = rs.getInt("UNIDADES_DISPONIBLES");

                JOptionPane.showMessageDialog(null,
                        "Nombre: " + nombre + "\n" +
                                "Valor Unitario: " + valorUnitario + "\n" +
                                "Unidades en Stock: " + UNIDADES_DISPONIBLES);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Producto searchProducto(String codigoABuscar) {
        String sql = "SELECT CODIGO, NOMBRE, VALOR_UNITARIO, UNIDADES_DISPONIBLES, ESTADO FROM PRODUCTO WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoABuscar);
            stmt.setString(2, Producto.ESTADO_ACTIVO);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getString("CODIGO"),
                        rs.getString("NOMBRE"),
                        rs.getDouble("VALOR_UNITARIO"),
                        rs.getInt("UNIDADES_DISPONIBLES"),
                        rs.getString("ESTADO")
                );
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean comprobarDisponibilidad(int cantidad, String codigoAEditar) {
        String sql = "SELECT UNIDADES_DISPONIBLES FROM PRODUCTO WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoAEditar);
            stmt.setString(2, Producto.ESTADO_ACTIVO);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stockActual = rs.getInt("UNIDADES_DISPONIBLES");
                return cantidad <= stockActual;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarDatos(String codigoAEditar, String codigoNuevo, String nombreNuevo, double valorNuevo, int nuevoStock) {
        String sql = "UPDATE PRODUCTO SET CODIGO = ?, NOMBRE = ?, VALOR_UNITARIO = ?, UNIDADES_DISPONIBLES = ? WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoNuevo);
            stmt.setString(2, nombreNuevo);
            stmt.setDouble(3, valorNuevo);
            stmt.setInt(4, nuevoStock);
            stmt.setString(5, codigoAEditar);
            stmt.setString(6, Producto.ESTADO_ACTIVO);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    public void abastecerStock(int cantidad, String codigoAEditar) {
        String sql = "UPDATE PRODUCTO SET UNIDADES_DISPONIBLES = UNIDADES_DISPONIBLES + ? WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cantidad);
            stmt.setString(2, codigoAEditar);
            stmt.setString(3, Producto.ESTADO_ACTIVO);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                Producto p = searchProducto(codigoAEditar);
                JOptionPane.showMessageDialog(null,
                        "Producto abastecido con éxito \nNuevo stock: " + p.getUnidadesDisponibles(),
                        null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void venderProducto(int cantidad, Producto producto) {
        String sql = "UPDATE PRODUCTO SET UNIDADES_DISPONIBLES = UNIDADES_DISPONIBLES - ? WHERE CODIGO = ? AND ESTADO = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cantidad);
            stmt.setString(2, producto.getCodigo());
            stmt.setString(3, Producto.ESTADO_ACTIVO);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}