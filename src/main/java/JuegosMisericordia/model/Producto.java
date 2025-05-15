package JuegosMisericordia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTO")
public class Producto {
    public static final String ESTADO_ACTIVO = "ACTIVO  ";
    public static final String ESTADO_INACTIVO = "INACTIVO";
    @Id
    private String codigo;

    private String nombre;
    private double valorUnitario;
    private int unidadesDisponibles;
    private String estado;


}
