package SystemITR.JosueGuinea1A.Clientes.Repository;

import SystemITR.JosueGuinea1A.Clientes.Models.Entity.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, Long> {
    boolean existsByEmail(String email);
}
