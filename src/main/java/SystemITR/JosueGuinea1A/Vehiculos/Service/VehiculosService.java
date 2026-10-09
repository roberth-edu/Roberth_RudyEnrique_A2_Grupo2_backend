package SystemITR.JosueGuinea1A.Vehiculos.Service;

import SystemITR.JosueGuinea1A.Clientes.Models.DTO.ClientesDTO;
import SystemITR.JosueGuinea1A.Clientes.Models.Entity.ClientesEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Models.DTO.VehiculosDTO;
import SystemITR.JosueGuinea1A.Vehiculos.Models.Entity.VehiculosEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Repository.VehiculosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehiculosService {

    private final VehiculosRepository repo;

    public VehiculosDTO nuevoVehiculo(@Valid VehiculosDTO dto)
    {
        try
        {
            VehiculosEntity entity = convertirEntity(dto);
            VehiculosEntity entitySave = repo.save(entity);
            return convertirDTO(entitySave);
        }
        catch (Exception e)
        {
            log.error("Error al guardar cliente");
            throw e;
        }
    }

    private VehiculosDTO convertirDTO(VehiculosEntity entity)
    {
        VehiculosDTO objDTO= new VehiculosDTO();
        objDTO.setIdVehiculo(entity.getIdVehiculo());
        objDTO.setMarca(entity.getMarca());
        objDTO.setCapacidad(entity.getCapacidad());
        objDTO.setPlaca(entity.getPlaca());
        objDTO.setModelo(entity.getModelo());
        objDTO.setPrecio_dia(entity.getPrecio_dia());
        return objDTO;
    }

    private VehiculosEntity convertirEntity(@Valid VehiculosDTO dto)
    {
        VehiculosEntity objEntity= new VehiculosEntity();
        objEntity.setIdVehiculo(dto.getIdVehiculo());
        objEntity.setMarca(dto.getMarca());
        objEntity.setCapacidad(dto.getCapacidad());
        objEntity.setPlaca(dto.getPlaca());
        objEntity.setModelo(dto.getModelo());
        objEntity.setPrecio_dia(dto.getPrecio_dia());
        return objEntity;
    }

    public List<VehiculosDTO> obtenerTodo()
    {
        List<VehiculosEntity> data = repo.findAll();

        return data.stream().map(this::convertirDTO).toList();
    }

    public VehiculosDTO buscarId(Long id)
    {
        VehiculosEntity entidad = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el vehiuclo con id "+ id));
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

    public VehiculosDTO actualizar(Long id, @Valid VehiculosDTO dto)
    {
        try
        {
            VehiculosEntity entidad = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el vehiculo para actualizar con id "+ id));;
            entidad.setMarca(dto.getMarca());
            entidad.setCapacidad(dto.getCapacidad());
            entidad.setPlaca(dto.getPlaca());
            entidad.setModelo(dto.getModelo());
            entidad.setPrecio_dia(dto.getPrecio_dia());

            VehiculosEntity datosGuardados = repo.save(entidad);
            return convertirDTO(datosGuardados);
        }
        catch (Exception e)
        {
            log.error("Error al actualizar vehiculo");
            throw e;
        }
    }
}
