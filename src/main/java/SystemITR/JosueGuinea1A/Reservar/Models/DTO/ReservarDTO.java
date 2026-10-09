package SystemITR.JosueGuinea1A.Reservar.Models.DTO;

import SystemITR.JosueGuinea1A.Clientes.Models.Entity.ClientesEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Models.Entity.VehiculosEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ReservarDTO {
    private Long idReserva;
    @NotNull
    private Long fkCliente;
    @NotNull
    private Long fkVehiculos;
    @NotBlank
    private String nombreReserva;
    @NotNull
    @Past(message = "No se permite reservar para fechas pasadas")
    private LocalDate fechaReserva;
    @NotNull
    @Min(value = 1)
    private int cantidadPasajeros;
    @NotNull
    @Min(value = 1)
    private int cantidadDias;
    @NotBlank
    @Pattern(regexp = "^(PENDIENTE|CONFIRMADA|CANCELADA|FINALIZADA)$")
    private String estado;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor que 0")
    private BigDecimal totalPago;
}
