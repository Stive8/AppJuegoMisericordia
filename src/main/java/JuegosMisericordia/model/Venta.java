package JuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "VENTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    private int numeroVenta;  // Generado manualmente desde Java

    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Empleado vendedor;

    @Column(nullable = false)
    private String tipoPago;

    @Column(nullable = false)
    private double montoTotal;

    private double cambio;
}
