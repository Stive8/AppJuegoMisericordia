package JuegosMisericordia.AppJuegosMisericordia.services;

import javax.swing.*;
import java.io.RandomAccessFile;

public class GestorVentas {
    private String pathNumero = "data\\numeroVenta.txt";

    public int generarNumeroVenta() {
        RandomAccessFile fileNumero = null;

        try {
            fileNumero = new RandomAccessFile(pathNumero, "rw");

            int nuevoNumeroVenta = fileNumero.readInt();
            nuevoNumeroVenta ++;

            fileNumero.seek(0);
            fileNumero.writeInt(nuevoNumeroVenta);

            fileNumero.close();

            return nuevoNumeroVenta;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
