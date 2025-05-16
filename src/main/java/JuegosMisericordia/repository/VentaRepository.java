package JuegosMisericordia.repository;

import JuegosMisericordia.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT COALESCE(MAX(v.numeroVenta), 0) FROM Venta v")
    int findUltimoNumeroVenta();
}
