package SystemITR.JosueGuinea1A.Reservar.Repository;

import SystemITR.JosueGuinea1A.Reservar.Models.Entity.ReservarEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface ReservarRepository extends JpaRepository<ReservarEntity, Long> {

    @Bean
    boolean existsByFkVehiculos_IdVehiculoAndFechaReservaAndEstadoIn(Long idvehiculo, LocalDate fecha, Collection<String> estados);
}
