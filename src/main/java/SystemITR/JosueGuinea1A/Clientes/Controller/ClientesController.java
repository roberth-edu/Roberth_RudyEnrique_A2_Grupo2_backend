package SystemITR.JosueGuinea1A.Clientes.Controller;

import SystemITR.JosueGuinea1A.Clientes.Models.DTO.ClientesDTO;
import SystemITR.JosueGuinea1A.Clientes.Service.ClientesService;
import SystemITR.JosueGuinea1A.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            if (dto == null)
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
}
