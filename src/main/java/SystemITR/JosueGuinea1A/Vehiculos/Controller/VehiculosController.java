package SystemITR.JosueGuinea1A.Vehiculos.Controller;

import SystemITR.JosueGuinea1A.Clientes.Models.DTO.ClientesDTO;
import SystemITR.JosueGuinea1A.Response.ApiResponse;
import SystemITR.JosueGuinea1A.Vehiculos.Models.DTO.VehiculosDTO;
import SystemITR.JosueGuinea1A.Vehiculos.Service.VehiculosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/vehiculo")
public class VehiculosController {
    private final VehiculosService service;

    public VehiculosController(VehiculosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehiculosDTO>> nuevoCliente (@Valid @RequestBody VehiculosDTO json)
    {
        try {
            VehiculosDTO dto = service.nuevoVehiculo(json);
            if (dto != null)
            {
                log.info("El vehiculo: "+ json + " se agrego con exito");
                ApiResponse<VehiculosDTO> respuesta = new ApiResponse<>(true, "vehiculo agregado con exito", json);
                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
            }
            log.warn("Error al eliminar cliente");
            ApiResponse<VehiculosDTO> respuestaFallida = new ApiResponse<>(false, "Error al agregar un nuevo vehiculo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar agregar un vehiculo");
            e.printStackTrace();
            ApiResponse<VehiculosDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar agregar un vehiculo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehiculosDTO>>> obtenerTodo()
    {
        try {
            List<VehiculosDTO> listaRoles = service.obtenerTodo();
            if (listaRoles != null && !listaRoles.isEmpty())
            {
                log.info("Los vehiculos se mostraron con exito");
                ApiResponse<List<VehiculosDTO>> respuesta = new ApiResponse<>(true, "vehiculos Consultados correctamente");
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Error al mostrar vehiculos");
            ApiResponse<List<VehiculosDTO>> respuestaFallida = new ApiResponse<>(false, "Error al agregar un nuevo vehiculos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar mostrar vehiculos");
            e.printStackTrace();
            ApiResponse<List<VehiculosDTO>> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar vehiculos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculosDTO>> obtenerClienteId(@PathVariable Long id)
    {
        try {
            VehiculosDTO data = service.buscarId(id);
            if (data != null) {
                log.info("Los vehiculos se mostraron con exito");
                ApiResponse<VehiculosDTO> respuesta = new ApiResponse<>(true, "vehiculos con id " + id + " consultado correctamente");
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Error al mostrar vehiculos");
            ApiResponse<VehiculosDTO> respuestaFallida = new ApiResponse<>(false, "Error al buscar un vehiculos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar mostrar vehiculos");
            e.printStackTrace();
            ApiResponse<VehiculosDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar vehiculos");
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
                log.info("El vehiculo: "+ id + " se elimino con exito");
                ApiResponse<Void> respuestaExitosa = new ApiResponse<>(true, "vehiculos eliminado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
            }
            log.warn("Error al eliminar vehiculos");
            ApiResponse<Void> respuestaFallida = new ApiResponse<>(false, "Error al eliminar un vehiculo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar eliminar clientes");
            e.printStackTrace();
            ApiResponse<Void> respuesta = new ApiResponse<>(false, "Error inesperado al intentar eliminar vehiculos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VehiculosDTO>> actualizarCliente (@PathVariable Long id, @Valid @RequestBody VehiculosDTO dto)
    {
        try
        {
            VehiculosDTO data = service.actualizar(id,dto);
            if (data != null)
            {
                log.info("El vehiculo: "+ id + " se actualizo con exito");
                ApiResponse<VehiculosDTO> respuestaExitosa = new ApiResponse<>(true, "vehiculo actualizado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
            }
            log.warn("Error al Actualizar clientes");
            ApiResponse<VehiculosDTO> respuestaFallida = new ApiResponse<>(false, "Error al eliminar un vehiculo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar actualizar vehiculos");
            e.printStackTrace();
            ApiResponse<VehiculosDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar vehiculos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
}
