package SystemITR.JosueGuinea1A.Reservar.Controller;

import SystemITR.JosueGuinea1A.Clientes.Models.DTO.ClientesDTO;
import SystemITR.JosueGuinea1A.Reservar.Models.DTO.ReservarDTO;
import SystemITR.JosueGuinea1A.Reservar.Service.ReservarService;
import SystemITR.JosueGuinea1A.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/reservas")
public class ReservarController {
    private final ReservarService service;

    public ReservarController(ReservarService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservarDTO>> nuevoCliente (@Valid @RequestBody ReservarDTO json)
    {
        try {
            ReservarDTO dto = service.nuevaReserva(json);
            if (dto != null)
            {
                log.info("la reserva: "+ json + " se agrego con exito");
                ApiResponse<ReservarDTO> respuesta = new ApiResponse<>(true, "reserva agregado con exito", json);
                return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
            }
            log.warn("Error al eliminar cliente");
            ApiResponse<ReservarDTO> respuestaFallida = new ApiResponse<>(false, "Error al agregar un nueva reserva");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar agregar una reserva");
            e.printStackTrace();
            ApiResponse<ReservarDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar agregar una reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservarDTO>>> obtenerTodo()
    {
        try {
            List<ReservarDTO> listaRoles = service.obtenerTodo();
            if (listaRoles != null && !listaRoles.isEmpty())
            {
                log.info("Las reservas se mostraron con exito");
                ApiResponse<List<ReservarDTO>> respuesta = new ApiResponse<>(true, "Reservas Consultadas correctamente");
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Error al mostrar reservas");
            ApiResponse<List<ReservarDTO>> respuestaFallida = new ApiResponse<>(false, "Error al agregar una nueva reserva");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar mostrar reservas");
            e.printStackTrace();
            ApiResponse<List<ReservarDTO>> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar reservas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservarDTO>> obtenerClienteId(@PathVariable Long id)
    {
        try {
            ReservarDTO data = service.buscarId(id);
            if (data != null) {
                log.info("Las reservas se mostraron con exito");
                ApiResponse<ReservarDTO> respuesta = new ApiResponse<>(true, "Reserva  con id " + id + " consultado correctamente");
                return ResponseEntity.ok(respuesta);
            }
            log.warn("Error al mostrar reservas");
            ApiResponse<ReservarDTO> respuestaFallida = new ApiResponse<>(false, "Error al buscar una Reserva");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar mostrar reservas");
            e.printStackTrace();
            ApiResponse<ReservarDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar reservas");
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
                log.info("la reserva: "+ id + " se elimino con exito");
                ApiResponse<Void> respuestaExitosa = new ApiResponse<>(true, "reserva eliminada con exito");
                return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
            }
            log.warn("Error al eliminar reserva");
            ApiResponse<Void> respuestaFallida = new ApiResponse<>(false, "Error al eliminar una reserva");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar eliminar reserva");
            e.printStackTrace();
            ApiResponse<Void> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservarDTO>> actualizarCliente (@PathVariable Long id, @Valid @RequestBody ReservarDTO dto)
    {
        try
        {
            ReservarDTO data = service.actualizar(id,dto);
            if (data != null)
            {
                log.info("La reserva: "+ id + " se actualizo con exito");
                ApiResponse<ReservarDTO> respuestaExitosa = new ApiResponse<>(true, "Reserva actualizado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
            }
            log.warn("Error al Actualizar reserva");
            ApiResponse<ReservarDTO> respuestaFallida = new ApiResponse<>(false, "Error al eliminar una reserva");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }
        catch (Exception e)
        {
            log.error("Error inesperado al intentar actualizar reserva");
            e.printStackTrace();
            ApiResponse<ReservarDTO> respuesta = new ApiResponse<>(false, "Error inesperado al intentar consultar reserva");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }
}
