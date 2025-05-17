package JuegosMisericordia.repository;

import JuegosMisericordia.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByUsernameAndPasswordAndEstado(String username, String password, String estado);
    Optional<Empleado> findByIdAndEstado(Long id, String estado);
}
