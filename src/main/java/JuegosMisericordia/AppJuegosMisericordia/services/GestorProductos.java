package JuegosMisericordia.AppJuegosMisericordia.services;

import JuegosMisericordia.AppJuegosMisericordia.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;

public class GestorProductos {

    public static final int TAM_MAX_CODIGO = 8;
    public static final int TAM_MAX_NOM = 30;
    public static final int TAMANIO_REGISTRO = (TAM_MAX_CODIGO + TAM_MAX_NOM + 8 + 4 + 10 + 4);// 1
    private final String path = "data\\producto.txt";


    public String setTamanioCod(String cadena) {
        if (cadena.length() < TAM_MAX_CODIGO) {
            int espFaltantes = TAM_MAX_CODIGO - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_CODIGO) {
            cadena = cadena.substring(0, TAM_MAX_CODIGO);
        }
        return cadena;
    }

    public String setTamanioNom(String cadena) {
        if (cadena.length() < TAM_MAX_NOM) {
            int espFaltantes = TAM_MAX_NOM - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_NOM) {
            cadena = cadena.substring(0, TAM_MAX_NOM);
        }
        return cadena;
    }

    public void addProducto(Producto producto) {
        RandomAccessFile fileProducto = null;
        try {
            fileProducto = new RandomAccessFile(path, "rw");

            fileProducto.seek(fileProducto.length());

            if (producto.getCodigo().length() > TAM_MAX_CODIGO) {
                JOptionPane.showMessageDialog(null, "Codigo demasiado largo (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (producto.getNombre().length() > TAM_MAX_NOM) {
                JOptionPane.showMessageDialog(null, "Nombre del producto demasiado largo (Máx. 20 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }


            fileProducto.writeUTF(setTamanioCod(producto.getCodigo()));
            fileProducto.writeUTF(setTamanioNom(producto.getNombre()));
            fileProducto.writeDouble(producto.getValorUnitario());
            fileProducto.writeInt(producto.getUnidadesDisponibles());
            fileProducto.writeUTF(producto.getEstado());

            fileProducto.close();

            JOptionPane.showMessageDialog(null, "Producto añadido exitosamente", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarProducto(String codigoABorrar){
        RandomAccessFile fileProducto = null;
        try {
            fileProducto = new RandomAccessFile(path, "rw");
            int cont = 0;
            String codigoLeido = "";
            String estado = "";

            while(true){
                codigoLeido = fileProducto.readUTF();
                fileProducto.readUTF();
                fileProducto.readDouble();
                fileProducto.readInt();
                estado = fileProducto.readUTF();
                cont++;

                if (codigoABorrar.trim().equals(codigoLeido.trim()) && estado.equals(Producto.ESTADO_ACTIVO)) {
                    fileProducto.seek((TAMANIO_REGISTRO * cont) - 10);
                    fileProducto.writeUTF(Producto.ESTADO_INACTIVO);
                    JOptionPane.showMessageDialog(null, "Producto eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
                    fileProducto.close();
                    return;
                }
                if (fileProducto.getFilePointer() == fileProducto.length()) {
                    break;
                }
            }

            fileProducto.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subirDatosATabla(DefaultTableModel modelo) {
        RandomAccessFile fileProductos = null;
        try {
            fileProductos = new RandomAccessFile(path, "rw");

            while (true) {
                String codigo = fileProductos.readUTF();
                String nombre = fileProductos.readUTF();
                double valorUnitario = fileProductos.readDouble();
                int unidadesDisponibles = fileProductos.readInt();
                String estado = fileProductos.readUTF();

                if (estado.equals(Producto.ESTADO_ACTIVO)) {
                    modelo.addRow(new Object[]{codigo, nombre, valorUnitario, unidadesDisponibles});
                }

                if (fileProductos.getFilePointer() == fileProductos.length()) {
                    break;
                }
            }

            fileProductos.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarProducto(String codigoABuscar){
        RandomAccessFile fileProducto = null;
        codigoABuscar = setTamanioCod(codigoABuscar);

        String estado = "";
        try {
            fileProducto = new RandomAccessFile(path, "rw");

            while (true) {
                String codigo = fileProducto.readUTF();
                String nombre = fileProducto.readUTF();
                double valorUnitario = fileProducto.readDouble();
                int unidadesDisponibles = fileProducto.readInt();
                estado = fileProducto.readUTF();
                if (codigo.equals(codigoABuscar) && estado.equals(Producto.ESTADO_ACTIVO)) {
                    JOptionPane.showMessageDialog(null, ("Nombre: "+nombre+"\n Valor Unitario: "+valorUnitario+"\n Unidades en Stock: " + unidadesDisponibles));
                    fileProducto.close();
                    return;
                }
                if(fileProducto.getFilePointer()== fileProducto.length()){
                    break;
                }
            }

            fileProducto.close();
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Producto searchProducto(String codigoABuscar){
        RandomAccessFile fileProducto = null;
        codigoABuscar = setTamanioCod(codigoABuscar);

        String estado = "";
        try {
            fileProducto = new RandomAccessFile(path, "rw");

            while (true) {
                String codigo = fileProducto.readUTF();
                String nombre = fileProducto.readUTF();
                double valorUnitario = fileProducto.readDouble();
                int unidadesDisponibles = fileProducto.readInt();
                estado = fileProducto.readUTF();
                if (codigo.trim().equals(codigoABuscar.trim()) && estado.equals(Producto.ESTADO_ACTIVO)) {
                    fileProducto.close();
                    return new Producto(codigo, nombre, valorUnitario,unidadesDisponibles,estado);
                }
                if(fileProducto.getFilePointer()== fileProducto.length()){
                    break;
                }
            }

            fileProducto.close();
            JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean comprobarDisponibilidad(int cantidad, String codigoAEditar){
        RandomAccessFile fileProducto = null;

        try {
            fileProducto = new RandomAccessFile(path, "rw");
            String codigoLeido = "";
            int stockActual = 0;
            while(true){
                codigoLeido = fileProducto.readUTF();
                fileProducto.readUTF();
                fileProducto.readDouble();
                stockActual = fileProducto.readInt();
                String estado = fileProducto.readUTF();

                if ((codigoLeido.trim().equals(codigoAEditar.trim()) && estado.equals(Producto.ESTADO_ACTIVO)) && (cantidad<=stockActual)) {
                    fileProducto.close();
                    return true;
                }
                if(fileProducto.getFilePointer()==fileProducto.length()){
                    break;
                }
            }

            fileProducto.close();
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarDatos(String codigoAEditar, String codigoNuevo, String nombreNuevo, double valorNuevo, int nuevoStock){
        RandomAccessFile fileProducto = null;

        if (codigoNuevo.length() > TAM_MAX_CODIGO) {
            JOptionPane.showMessageDialog(null, "Código demasiado largo (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nombreNuevo.length() > TAM_MAX_NOM) {
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo (Máx. 30 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            fileProducto = new RandomAccessFile(path, "rw");
            String codigoLeido;
            while(true){
                codigoLeido = fileProducto.readUTF();
                fileProducto.readUTF();
                fileProducto.readDouble();
                fileProducto.readInt();
                String estado = fileProducto.readUTF();

                if (codigoLeido.trim().equals(codigoAEditar.trim()) && estado.equals(Producto.ESTADO_ACTIVO)) {
                    fileProducto.seek(fileProducto.getFilePointer()-TAMANIO_REGISTRO);
                    fileProducto.writeUTF(setTamanioCod(codigoNuevo));
                    fileProducto.writeUTF(setTamanioNom(nombreNuevo));
                    fileProducto.writeDouble(valorNuevo);
                    fileProducto.writeInt(nuevoStock);

                    JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            fileProducto.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void abastecerStock(int cantidad, String codigoAEditar){
        RandomAccessFile fileProducto = null;

        try {
            fileProducto = new RandomAccessFile(path, "rw");
            String codigoLeido;
            int stockActual = 0;
            while(true){
                codigoLeido = fileProducto.readUTF();
                fileProducto.readUTF();
                fileProducto.readDouble();
                stockActual = fileProducto.readInt();
                String estado = fileProducto.readUTF();

                if (codigoLeido.trim().equals(codigoAEditar.trim()) && estado.equals(Producto.ESTADO_ACTIVO)) {
                    stockActual += cantidad;
                    fileProducto.seek(fileProducto.getFilePointer()-10-4);
                    fileProducto.writeInt(stockActual);

                    JOptionPane.showMessageDialog(null, ("Producto abastecido con éxito \n Nuevo stock: " + stockActual), null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            fileProducto.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void venderProducto(int cantidad, Producto producto){
        RandomAccessFile fileProducto = null;

        try {
            fileProducto = new RandomAccessFile(path, "rw");
            String codigo = producto.getCodigo();
            int stockActual = 0;
            while(true){
                String codigoLeido = fileProducto.readUTF();
                fileProducto.readUTF();
                fileProducto.readDouble();
                stockActual = fileProducto.readInt();
                String estado = fileProducto.readUTF();

                if (codigoLeido.trim().equals(codigo.trim()) && estado.equals(Producto.ESTADO_ACTIVO)) {
                    stockActual -= cantidad;
                    fileProducto.seek(fileProducto.getFilePointer()-10-4);
                    fileProducto.writeInt(stockActual);
                    break;
                }
            }

            fileProducto.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
