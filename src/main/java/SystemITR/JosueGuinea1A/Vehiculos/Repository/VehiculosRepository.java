package SystemITR.JosueGuinea1A.Vehiculos.Repository;

import SystemITR.JosueGuinea1A.Vehiculos.Models.Entity.VehiculosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculosRepository extends JpaRepository<VehiculosEntity, Long> {
}
