package SystemITR.JosueGuinea1A.Clientes.Controller;

import SystemITR.JosueGuinea1A.Clientes.Models.DTO.ClientesDTO;
import SystemITR.JosueGuinea1A.Clientes.Service.ClientesService;
import SystemITR.JosueGuinea1A.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClientesService service;

    public ClientesController(ClientesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientesDTO>> nuevoCliente (@Valid @RequestBody ClientesDTO json)
    {
        try {
            ClientesDTO dto = service.nuevoCliente(json);
            if (dto != null)
            {
                log.info("El cliente: "+ json + " se agrego con exito");
                ApiResponse<ClientesDTO> respuesta = new ApiResponse<>(true, "Cliente agregado con exito", json);
                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
            }
            log.warn("Error al eliminar cliente");
            ApiResponse<ClientesDTO> respuestaFallida = new ApiResponse<>(false, "Error al agregar un nuevo cliente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar agregar un cliente");
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar agregar un cliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientesDTO>>> obtenerTodo()
    {
        try {
            List<ClientesDTO> listaRoles = service.obtenerTodo();
            if (listaRoles != null && !listaRoles.isEmpty())
            {
                log.info("Los clientes se mostraron con exito");
                ApiResponse<List<ClientesDTO>> respuesta = new ApiResponse<>(true, "Clientes Consultados correctamente");
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Error al mostrar clientes");
            ApiResponse<List<ClientesDTO>> respuestaFallida = new ApiResponse<>(false, "Error al agregar un nuevo cliente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar mostrar clientes");
            e.printStackTrace();
            ApiResponse<List<ClientesDTO>> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar clientes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> obtenerClienteId(@PathVariable Long id)
    {
        try {
            ClientesDTO data = service.buscarId(id);
            if (data != null) {
                log.info("Los clientes se mostraron con exito");
                ApiResponse<ClientesDTO> respuesta = new ApiResponse<>(true, "Cliente con id " + id + " consultado correctamente");
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Error al mostrar clientes");
            ApiResponse<ClientesDTO> respuestaFallida = new ApiResponse<>(false, "Error al buscar un cliente");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar mostrar clientes");
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar clientes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCliente (@PathVariable Long id)
    {
        try
        {
            boolean respuesta = service.eliminar(id);
            if (respuesta)
            {
                log.info("El cliente: "+ id + " se elimino con exito");
                ApiResponse<Void> respuestaExitosa = new ApiResponse<>(true, "Cliente eliminado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
            }
            log.warn("Error al eliminar clientes");
            ApiResponse<Void> respuestaFallida = new ApiResponse<>(false, "Error al eliminar un cliente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar eliminar clientes");
            e.printStackTrace();
            ApiResponse<Void> respuesta = new ApiResponse<>(false, "Error inesperado al intentar eliminar clientes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientesDTO>> actualizarCliente (@PathVariable Long id, @Valid @RequestBody ClientesDTO dto)
    {
        try
        {
            ClientesDTO data = service.actualizar(id,dto);
            if (data != null)
            {
                log.info("El cliente: "+ id + " se actualizo con exito");
                ApiResponse<ClientesDTO> respuestaExitosa = new ApiResponse<>(true, "Cliente actualizado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
            }
            log.warn("Error al Actualizar clientes");
            ApiResponse<ClientesDTO> respuestaFallida = new ApiResponse<>(false, "Error al eliminar un cliente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar actualizar clientes");
            e.printStackTrace();
            ApiResponse<ClientesDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar clientes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

}
