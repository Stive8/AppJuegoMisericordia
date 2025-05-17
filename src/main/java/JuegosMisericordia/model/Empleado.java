package JuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EMPLEADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    public static final String ESTADO_ACTIVO = "ACTIVO";
    public static final String ESTADO_INACTIVO = "INACTIVO";
    public static final String ROL_ADMINISTRADOR = "ADMINISTRADOR";
    public static final String ROL_VENDEDOR = "VENDEDOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;  // ID numérico autoincremental

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private double salario;
    private String estado;
    private String rol;

    // Constructor sin el parámetro ID
    public Empleado(String username, String password, double salario, String estado, String rol) {
        this.username = username;
        this.password = password;
        this.salario = salario;
        this.estado = estado;
        this.rol = rol;
    }
}