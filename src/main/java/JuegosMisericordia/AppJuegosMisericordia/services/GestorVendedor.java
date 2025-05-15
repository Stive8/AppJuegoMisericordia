package JuegosMisericordia.AppJuegosMisericordia.services;

import JuegosMisericordia.AppJuegosMisericordia.model.Empleado;
import JuegosMisericordia.AppJuegosMisericordia.model.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;

public class GestorVendedor {

    public static final int TAM_MAX_ID = 8;
    public static final int TAM_MAX_USERNAME = 15;
    public static final int TAM_MAX_PASSWORD = 15;
    public static final int TAM_REGISTRO = (TAM_MAX_ID + TAM_MAX_USERNAME + TAM_MAX_PASSWORD + 8 + 10 + 6); //8 del salario(double), 10 del estado, 6 de los bytes extras
    private String path = "data\\vendedor.txt";

    public String setTamanioID(String cadena) {
        if (cadena.length() < TAM_MAX_ID) {
            int espFaltantes = TAM_MAX_ID - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_ID) {
            cadena = cadena.substring(0, TAM_MAX_ID);
        }
        return cadena;
    }
    public String setTamanioUsername(String cadena) {
        if (cadena.length() < TAM_MAX_USERNAME) {
            int espFaltantes = TAM_MAX_USERNAME - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_USERNAME) {
            cadena = cadena.substring(0, TAM_MAX_USERNAME);
        }
        return cadena;
    }
    public String setTamanioPassword(String cadena) {
        if (cadena.length() < TAM_MAX_PASSWORD) {
            int espFaltantes = TAM_MAX_PASSWORD - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_PASSWORD) {
            cadena = cadena.substring(0, TAM_MAX_PASSWORD);
        }
        return cadena;
    }

    public void addSeller(Vendedor vendedor) {
        RandomAccessFile fileSeller = null;
        try {
            fileSeller = new RandomAccessFile(path, "rw");

            fileSeller.seek(fileSeller.length());

            if(vendedor.getId().length()>TAM_MAX_ID){
                JOptionPane.showMessageDialog(null, "ID Demasiado larga (Máx. 8 caracteres)", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else if(vendedor.getUsername().length()>TAM_MAX_USERNAME){
                JOptionPane.showMessageDialog(null, "Nombre de usuario demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if(vendedor.getPassword().length()>TAM_MAX_PASSWORD){
                JOptionPane.showMessageDialog(null, "Contraseña demasiado larga (Máx. 15 caracteres)", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            fileSeller.writeUTF(setTamanioID(vendedor.getId()));
            fileSeller.writeUTF(setTamanioUsername(vendedor.getUsername()));
            fileSeller.writeUTF(setTamanioPassword(vendedor.getPassword()));
            fileSeller.writeDouble(vendedor.getSalario());
            fileSeller.writeUTF(vendedor.getEstado());

            fileSeller.close();

            JOptionPane.showMessageDialog(null, "Vendedor registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSeller(String idBorrar){
        RandomAccessFile fileSeller = null;
        try {
            fileSeller = new RandomAccessFile(path, "rw");
            int cont = 0;
            idBorrar = setTamanioID(idBorrar);

            while (true) {
                String id = fileSeller.readUTF();
                fileSeller.readUTF();
                fileSeller.readUTF();
                fileSeller.readDouble();
                String estado = fileSeller.readUTF();
                cont++;

                if (idBorrar.equals(id)) {
                    fileSeller.seek((TAM_REGISTRO * cont) - 10);
                    fileSeller.writeUTF(Empleado.ESTADO_INACTIVO);
                    JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
                    fileSeller.close();
                    return;
                }
                if(fileSeller.getFilePointer()==fileSeller.length()){
                    break;
                }
            }

            fileSeller.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean buscarSeller(String id) {
        RandomAccessFile fileSeller = null;
        id = setTamanioID(id);
        String idLeida;
        String estado = "";
        try {
            fileSeller = new RandomAccessFile(path, "rw");

            while (true) {
                idLeida = fileSeller.readUTF();
                String nombre = fileSeller.readUTF();
                fileSeller.readUTF();
                double salario = fileSeller.readDouble();
                estado = fileSeller.readUTF();
                if (idLeida.equals(id) && estado.equals(Empleado.ESTADO_ACTIVO)) {
                    JOptionPane.showMessageDialog(null, ("Nombre: "+nombre+"\n Salario: "+salario+"\n Rol: Vendedor"));
                    fileSeller.close();
                    return true;
                }
                if(fileSeller.getFilePointer()== fileSeller.length()){
                    break;
                }
            }

            fileSeller.close();
            JOptionPane.showMessageDialog(null, "Empleado no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editarRegistro(String idSeleccionada, String idNueva, String nombreNuevo, double salarioNuevo) {
        RandomAccessFile fileSeller = null;

        if (idNueva.length() > TAM_MAX_ID) {
            JOptionPane.showMessageDialog(null, "ID demasiado larga (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nombreNuevo.length() > TAM_MAX_USERNAME) {
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            fileSeller = new RandomAccessFile(path, "rw");
            String idLeida = "";
            String passwordLeida = "";
            String estadoLeido = "";

            while (true) {
                idLeida = fileSeller.readUTF();
                fileSeller.readUTF();
                passwordLeida = fileSeller.readUTF();
                fileSeller.readDouble();
                estadoLeido = fileSeller.readUTF();

                if ((idLeida.equals(idSeleccionada)) && (estadoLeido.equals(Empleado.ESTADO_ACTIVO))) {

                    fileSeller.seek(fileSeller.getFilePointer() - TAM_REGISTRO);

                    fileSeller.writeUTF(setTamanioID(idNueva));
                    fileSeller.writeUTF(setTamanioUsername(nombreNuevo));
                    fileSeller.writeUTF(passwordLeida);
                    fileSeller.writeDouble(salarioNuevo);
                    fileSeller.writeUTF(estadoLeido);

                    JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            fileSeller.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificarRegistro(String username, String password) {
        RandomAccessFile fileSeller = null;
        String username2 = setTamanioUsername(username);
        String password2 = setTamanioPassword(password);
        String usernameLeido;
        String passwordLeida;
        try {
            fileSeller = new RandomAccessFile(path, "rw");

            while (true) {
                fileSeller.readUTF();
                usernameLeido = fileSeller.readUTF();
                passwordLeida = fileSeller.readUTF();
                fileSeller.readDouble();
                fileSeller.readUTF();
                if ((usernameLeido.equals(username2)) && (passwordLeida.equals(password2))) {
                    return true;
                }
                if(fileSeller.getFilePointer()== fileSeller.length()){
                    break;
                }
            }

            fileSeller.close();
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subirDatosATabla(DefaultTableModel modelo){
        RandomAccessFile fileSeller = null;
        try {
            fileSeller = new RandomAccessFile(path, "rw");

            while(true){
                String id = fileSeller.readUTF();
                String nombre = fileSeller.readUTF();
                fileSeller.readUTF(); //Lee la contraseña pero la pasa de largo
                double salario = fileSeller.readDouble();
                String estado = fileSeller.readUTF();

                if(estado.equals(Empleado.ESTADO_ACTIVO)){
                    modelo.addRow(new Object[]{id, nombre, salario, "VENDEDOR"});
                }

                if(fileSeller.getFilePointer()== fileSeller.length()){
                    break;
                }
            }

            fileSeller.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
