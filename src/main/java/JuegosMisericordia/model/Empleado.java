package JuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EMPLEADO")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    public static final String ESTADO_ACTIVO = "ACTIVO";
    public static final String ESTADO_INACTIVO = "INACTIVO";
    public static final String ROL_ADMINISTRADOR = "ADMINISTRADOR";
    public static final String ROL_VENDEDOR = "VENDEDOR";

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private double salario;
    private String estado;
    private String rol;
}
