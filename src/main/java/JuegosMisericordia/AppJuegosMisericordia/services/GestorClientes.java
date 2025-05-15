package JuegosMisericordia.AppJuegosMisericordia.services;


import JuegosMisericordia.AppJuegosMisericordia.model.Cliente;

import javax.swing.*;
import java.io.RandomAccessFile;

public class GestorClientes {

    public static final int TAM_MAX_CEDULA = 10;
    public static final int TAM_MAX_CELULAR = 10;
    public static final int TAM_MAX_CORREO = 40;
    public static final int TAM_REGISTRO = (TAM_MAX_CEDULA + TAM_MAX_CELULAR + TAM_MAX_CORREO + 6);
    private String path = "data\\cliente.txt";


    public String setTamanioCedula(String cadena) {
        if (cadena.length() < TAM_MAX_CEDULA) {
            int espFaltantes = TAM_MAX_CEDULA - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_CEDULA) {
            cadena = cadena.substring(0, TAM_MAX_CEDULA);
        }
        return cadena;
    }

    public String setTamanioCelular(String cadena) {
        if (cadena.length() < TAM_MAX_CELULAR) {
            int espFaltantes = TAM_MAX_CELULAR - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_CELULAR) {
            cadena = cadena.substring(0, TAM_MAX_CELULAR);
        }
        return cadena;
    }

    public String setTamanioCorreo(String cadena) {
        if (cadena.length() < TAM_MAX_CORREO) {
            int espFaltantes = TAM_MAX_CORREO - cadena.length();
            cadena = cadena + " ".repeat(espFaltantes);
        } else if (cadena.length() > TAM_MAX_CORREO) {
            cadena = cadena.substring(0, TAM_MAX_CORREO);
        }
        return cadena;
    }

    public void addCliente(Cliente cliente) {
        RandomAccessFile fileCliente = null;
        try {
            fileCliente= new RandomAccessFile(path, "rw");

            fileCliente.seek(fileCliente.length());

            if (cliente.getCedula().length() > TAM_MAX_CEDULA) {
                JOptionPane.showMessageDialog(null, "Cedula demasiado larga (Máx. 10 caracteres) \n Verifique que la información sea correcta", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (cliente.getCelular().length() > TAM_MAX_CELULAR) {
                JOptionPane.showMessageDialog(null, "Numero de celular demasiado largo (Máx.10 caracteres \n Verifique que la informacion sea correcta", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (cliente.getCorreo().length() > TAM_MAX_CORREO) {
                JOptionPane.showMessageDialog(null, "Correo demasiado largo (Máx. 40 caracteres)", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            fileCliente.writeUTF(setTamanioCedula(cliente.getCedula()));
            fileCliente.writeUTF(setTamanioCelular(cliente.getCelular()));
            fileCliente.writeUTF(setTamanioCorreo(cliente.getCorreo()));

            fileCliente.close();

            JOptionPane.showMessageDialog(null, "Cliente registrado registrado exitosamente", null, JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente searchCliente(String cedulaABuscar) {
        RandomAccessFile fileCliente = null;

        try {
            fileCliente = new RandomAccessFile(path, "rw");

            while (true) {
                String cedulaLeida = fileCliente.readUTF();
                String celularLeido = fileCliente.readUTF();
                String correoLeido = fileCliente.readUTF();

                if (cedulaLeida.trim().equals(cedulaABuscar.trim())) {
                    return new Cliente(cedulaLeida, celularLeido, correoLeido);
                }
                if (fileCliente.getFilePointer() == fileCliente.length()) {
                    break;
                }
            }

            fileCliente.close();
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
