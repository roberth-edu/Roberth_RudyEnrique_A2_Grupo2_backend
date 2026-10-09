package SystemITR.JosueGuinea1A.Clientes.Service;
import SystemITR.JosueGuinea1A.Clientes.Models.DTO.ClientesDTO;
import SystemITR.JosueGuinea1A.Clientes.Models.Entity.ClientesEntity;
import SystemITR.JosueGuinea1A.Clientes.Repository.ClientesRepository;
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
public class ClientesService {

    private final ClientesRepository repo;

    public ClientesDTO nuevoCliente(@Valid ClientesDTO dto)
    {
        try
        {
            ClientesEntity entity = convertirEntity(dto);
            ClientesEntity entitySave = repo.save(entity);
            return convertirDTO(entitySave);
        }
        catch (Exception e)
        {
            log.error("Error al guardar cliente");
            throw e;
        }
    }

    private ClientesDTO convertirDTO(ClientesEntity entity)
    {
        ClientesDTO objDTO = new ClientesDTO();
        objDTO.setIdCliente(entity.getIdCliente());
        objDTO.setNombre(entity.getNombre());
        objDTO.setApellido(entity.getApellido());
        objDTO.setTelefono(entity.getTelefono());
        objDTO.setEmail(entity.getEmail());
        objDTO.setDireccion(entity.getDireccion());
        return objDTO;
    }

    private ClientesEntity convertirEntity(@Valid ClientesDTO dto)
    {
        ClientesEntity objEntity = new ClientesEntity();
        objEntity.setIdCliente(dto.getIdCliente());
        objEntity.setNombre(dto.getNombre());
        objEntity.setApellido(dto.getApellido());
        objEntity.setTelefono(dto.getTelefono());
        objEntity.setDireccion(dto.getDireccion());
        objEntity.setEmail(dto.getEmail());
        return objEntity;
    }

    public List<ClientesDTO> obtenerTodo()
    {
        List<ClientesEntity> data = repo.findAll();

        return data.stream().map(this::convertirDTO).toList();
    }

    public ClientesDTO buscarId(Long id)
    {
        ClientesEntity entidad = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el cliente con id "+ id));
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

    public ClientesDTO actualizar(Long id, @Valid ClientesDTO dto)
    {
        try
        {
            ClientesEntity entidad = repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el cliente para actualizar con id "+ id));;
            entidad.setNombre(dto.getNombre());
            entidad.setApellido(dto.getApellido());
            entidad.setTelefono(dto.getTelefono());
            entidad.setDireccion(dto.getDireccion());
            entidad.setEmail(dto.getEmail());

            ClientesEntity datosGuardados = repo.save(entidad);
            return convertirDTO(datosGuardados);
        }
        catch (Exception e)
        {
            log.error("Error al actualizar cliente");
            throw e;
        }
    }
}
