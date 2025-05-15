package JuegosMisericordia.services;

import JuegosMisericordia.model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.RandomAccessFile;

public class GestorAdministrador {

    public static final int TAM_MAX_ID = 8;
    public static final int TAM_MAX_USERNAME = 15;
    public static final int TAM_MAX_PASSWORD = 15;
    public static final int TAM_REGISTRO = (TAM_MAX_ID + TAM_MAX_USERNAME + TAM_MAX_PASSWORD + 8 + 10 + 6); //8 del salario(double), 10 del estado, 6 de los bytes extras
    private String path = "data\\administrador.txt";


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

    public void addAdmin(Empleado administrador) {
        RandomAccessFile fileAdmin = null;
        try {
            fileAdmin = new RandomAccessFile(path, "rw");

            fileAdmin.seek(fileAdmin.length());

            if (administrador.getId().length() > TAM_MAX_ID) {
                JOptionPane.showMessageDialog(null, "ID Demasiado larga (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (administrador.getUsername().length() > TAM_MAX_USERNAME) {
                JOptionPane.showMessageDialog(null, "Nombre de usuario demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (administrador.getPassword().length() > TAM_MAX_PASSWORD) {
                JOptionPane.showMessageDialog(null, "Contraseña demasiado larga (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            fileAdmin.writeUTF(setTamanioID(administrador.getId()));
            fileAdmin.writeUTF(setTamanioUsername(administrador.getUsername()));
            fileAdmin.writeUTF(setTamanioPassword(administrador.getPassword()));
            fileAdmin.writeDouble(administrador.getSalario());
            fileAdmin.writeUTF(administrador.getEstado());

            fileAdmin.close();

            JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAdmin(String idBorrar) {
        RandomAccessFile fileAdmin = null;
        try {
            fileAdmin = new RandomAccessFile(path, "rw");
            int cont = 0;
            idBorrar = setTamanioID(idBorrar);

            while (true) {
                String id = fileAdmin.readUTF();
                fileAdmin.readUTF();
                fileAdmin.readUTF();
                fileAdmin.readDouble();
                String estado = fileAdmin.readUTF();
                cont++;

                if (idBorrar.equals(id) && estado.equals(Empleado.ESTADO_ACTIVO)) {
                    fileAdmin.seek((TAM_REGISTRO * cont) - 10);
                    fileAdmin.writeUTF(Empleado.ESTADO_INACTIVO);
                    JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito", null, JOptionPane.INFORMATION_MESSAGE);
                    fileAdmin.close();
                    return;
                }
                if (fileAdmin.getFilePointer() == fileAdmin.length()) {
                    break;
                }
            }

            fileAdmin.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificarRegistro(String username, String password) {
        RandomAccessFile fileAdmin = null;
        String username2 = setTamanioUsername(username);
        String password2 = setTamanioPassword(password);
        String usernameLeido;
        String passwordLeida;
        String estado = "";
        try {
            fileAdmin = new RandomAccessFile(path, "rw");

            while (true) {
                fileAdmin.readUTF();
                usernameLeido = fileAdmin.readUTF();
                passwordLeida = fileAdmin.readUTF();
                fileAdmin.readDouble();
                estado = fileAdmin.readUTF();
                if (((usernameLeido.equals(username2)) && (passwordLeida.equals(password2))) && estado.equals(Empleado.ESTADO_ACTIVO)) {
                    return true;
                }
                if (fileAdmin.getFilePointer() == fileAdmin.length()) {
                    break;
                }
            }

            fileAdmin.close();
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editarRegistro(String idSeleccionada, String idNueva, String nombreNuevo, double salarioNuevo) {
        RandomAccessFile fileAdmin = null;

        if (idNueva.length() > TAM_MAX_ID) {
            JOptionPane.showMessageDialog(null, "ID demasiado larga (Máx. 8 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nombreNuevo.length() > TAM_MAX_USERNAME) {
            JOptionPane.showMessageDialog(null, "Nombre demasiado largo (Máx. 15 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            fileAdmin = new RandomAccessFile(path, "rw");
            String idLeida = "";
            String passwordLeida = "";
            String estadoLeido = "";

            while (true) {
                idLeida = fileAdmin.readUTF();
                fileAdmin.readUTF();
                passwordLeida = fileAdmin.readUTF();
                fileAdmin.readDouble();
                estadoLeido = fileAdmin.readUTF();

                if ((idLeida.equals(idSeleccionada)) && (estadoLeido.equals(Empleado.ESTADO_ACTIVO))) {

                    fileAdmin.seek(fileAdmin.getFilePointer() - TAM_REGISTRO);

                    fileAdmin.writeUTF(setTamanioID(idNueva));
                    fileAdmin.writeUTF(setTamanioUsername(nombreNuevo));
                    fileAdmin.writeUTF(passwordLeida);
                    fileAdmin.writeDouble(salarioNuevo);
                    fileAdmin.writeUTF(estadoLeido);

                    JOptionPane.showMessageDialog(null, "Cambio exitoso", null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

            fileAdmin.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean buscarAdmin(String id) {
        RandomAccessFile fileAdmin = null;
        id = setTamanioID(id);
        String idLeida;
        String estado = "";
        try {
            fileAdmin = new RandomAccessFile(path, "rw");

            while (true) {
                idLeida = fileAdmin.readUTF();
                String nombre = fileAdmin.readUTF();
                fileAdmin.readUTF();
                double salario = fileAdmin.readDouble();
                estado = fileAdmin.readUTF();
                if (idLeida.equals(id) && estado.equals(Empleado.ESTADO_ACTIVO)) {
                    JOptionPane.showMessageDialog(null, ("Nombre: " + nombre + "\n Salario: " + salario + "\n Rol: Empleado"));
                    fileAdmin.close();
                    return true;
                }
                if (fileAdmin.getFilePointer() == fileAdmin.length()) {
                    break;
                }
            }

            fileAdmin.close();
            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void subirDatosATabla(DefaultTableModel modelo) {
        RandomAccessFile fileAdmin = null;
        try {
            fileAdmin = new RandomAccessFile(path, "rw");

            while (true) {
                String id = fileAdmin.readUTF();
                String nombre = fileAdmin.readUTF();
                fileAdmin.readUTF(); //Lee la contraseña pero la pasa de largo
                double salario = fileAdmin.readDouble();
                String estado = fileAdmin.readUTF();

                if (estado.equals(Empleado.ESTADO_ACTIVO)) {
                    modelo.addRow(new Object[]{id, nombre, salario, "ADMINISTRADOR"});
                }

                if (fileAdmin.getFilePointer() == fileAdmin.length()) {
                    break;
                }
            }

            fileAdmin.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
