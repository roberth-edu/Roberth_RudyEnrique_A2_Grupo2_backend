package SystemITR.JosueGuinea1A.Reservar.Service;


import SystemITR.JosueGuinea1A.Reservar.Models.DTO.ReservarDTO;
import SystemITR.JosueGuinea1A.Reservar.Models.Entity.ReservarEntity;
import SystemITR.JosueGuinea1A.Reservar.Repository.ReservarRepository;
import SystemITR.JosueGuinea1A.Vehiculos.Models.DTO.VehiculosDTO;
import SystemITR.JosueGuinea1A.Vehiculos.Models.Entity.VehiculosEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Repository.VehiculosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservarService
{
    private final ReservarRepository repo;
    private final VehiculosRepository vehiculos;



    public ReservarDTO nuevaReserva(@Valid ReservarDTO dto)
    {
        try
        {

            List<String> estadosActivos = List.of("Confirmada", "Pendiente");

            boolean ocupado = repo.existsByFkVehiculos_IdVehiculoAndFechaReservaAndEstadoIn(dto.getFkVehiculos(), dto.getFechaReserva(), estadosActivos);

            if (ocupado)
            {
                throw new IllegalArgumentException("El vehiculo esta reservado en esa fecha");
            }

            ReservarEntity entity = convertirEntity(dto);
            entity.setEstado("CONFIRMADA");
            ReservarEntity entitySave = repo.save(entity);
            return convertirDTO(entitySave);
        }
        catch (Exception e)
        {
            log.error("Error al guardar cliente");
            throw e;
        }
    }

    private ReservarDTO convertirDTO(ReservarEntity entity)
    {
        ReservarDTO objDto = new ReservarDTO();
        objDto.setIdReserva(entity.getIdReserva());
        objDto.setEstado(entity.getEstado());
        objDto.setFechaReserva(entity.getFechaReserva());
        objDto.setCantidadDias(entity.getCantidadDias());
        objDto.setNombreReserva(entity.getNombreReserva());
        objDto.setCantidadPasajeros(entity.getCantidadPasajeros());
        return objDto;

    }

    private ReservarEntity convertirEntity(@Valid ReservarDTO dto)
    {
    ReservarEntity objEntity = new ReservarEntity();
        objEntity.setIdReserva(dto.getIdReserva());
        objEntity.setEstado(dto.getEstado());
        objEntity.setFechaReserva(dto.getFechaReserva());
        objEntity.setCantidadDias(dto.getCantidadDias());
        objEntity.setNombreReserva(dto.getNombreReserva());
        objEntity.setCantidadPasajeros(dto.getCantidadPasajeros());
        return objEntity;
    }

    public List<ReservarDTO> obtenerTodo()
    {
        List<ReservarEntity> data = repo.findAll();

        return data.stream().map(this::convertirDTO).toList();
    }

    public ReservarDTO buscarId(Long id)
    {
        ReservarEntity entidad = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro la reserva con id "+ id));
        return convertirDTO(entidad);
    }

    public boolean eliminar(Long id)
    {
        if (repo.existsById(id))
        {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public ReservarDTO actualizar(Long id, @Valid ReservarDTO dto)
    {
        try
        {

            ReservarEntity entidad = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro la reserva para actualizar con id "+ id));;
            entidad.setEstado(dto.getEstado());
            entidad.setFechaReserva(dto.getFechaReserva());
            entidad.setCantidadDias(dto.getCantidadDias());
            entidad.setNombreReserva(dto.getNombreReserva());
            entidad.setCantidadPasajeros(dto.getCantidadPasajeros());
            ReservarEntity datosGuardados = repo.save(entidad);
            return convertirDTO(datosGuardados);
        }
        catch (Exception e)
        {
            log.error("Error al actualizar reserva");
            throw e;
        }
    };

}
