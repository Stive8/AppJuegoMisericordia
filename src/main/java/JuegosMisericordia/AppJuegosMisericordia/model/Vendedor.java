package JuegosMisericordia.AppJuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "VENDEDOR")
public class Vendedor extends Empleado {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private double salario;

    private String estado;

    public Vendedor(String estado, String id, String password, double salario, String username) {
        this.estado = estado;
        this.id = id;
        this.password = password;
        this.salario = salario;
        this.username = username;
    }
}