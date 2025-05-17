package JuegosMisericordia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VENTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @Column(name = "NUMERO_VENTA")
    private Integer numeroVenta;

    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "VENDEDOR_ID", referencedColumnName = "ID")
    private Empleado vendedor;

    @Column(name = "TIPO_PAGO", nullable = false)
    private String tipoPago;

    @Column(name = "MONTO_TOTAL", nullable = false)
    private Double montoTotal;

    @Column(name = "CAMBIO")
    private Double cambio;

    @Column(name = "REEMBOLSADO", nullable = false)
    private String reembolsado = "NO"; // Valor por defecto

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();
}