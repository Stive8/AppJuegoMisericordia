package JuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTE")
public class Cliente {


    @Id
    private String cedula;
    private String celular;
    private String correo;


}
