package SystemITR.JosueGuinea1A.Clientes.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientesDTO {
    private Long idCliente;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String telefono;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String direccion;

}
