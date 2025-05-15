package JuegosMisericordia.AppJuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    private Long id;

    private String cedula;
    private String celular;
    private String correo;

    public Cliente(String cedula, String celular, String correo) {
        this.cedula = cedula;
        this.celular = celular;
        this.correo = correo;
    }
}
