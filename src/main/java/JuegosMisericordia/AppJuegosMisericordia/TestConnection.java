package JuegosMisericordia.AppJuegosMisericordia;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@192.168.101.74:1521:XE";
        String username = "DAE2024";
        String password = "DAE2024";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Conexión exitosa!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}