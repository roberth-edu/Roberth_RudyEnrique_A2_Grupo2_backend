package SystemITR.JosueGuinea1A.Vehiculos.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class VehiculosDTO {
    private Long idVehiculo;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotNull
    @Positive
    private int Capacidad;
    @NotNull

    private BigDecimal precio_dia;
    @NotBlank
    private String placa;
}
