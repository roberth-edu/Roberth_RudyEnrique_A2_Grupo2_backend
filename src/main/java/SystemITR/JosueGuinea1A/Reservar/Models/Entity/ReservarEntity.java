package SystemITR.JosueGuinea1A.Reservar.Models.Entity;

import SystemITR.JosueGuinea1A.Clientes.Models.Entity.ClientesEntity;
import SystemITR.JosueGuinea1A.Vehiculos.Models.Entity.VehiculosEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reservas")
public class ReservarEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    @Id
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false, unique = true)
    private ClientesEntity fkCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculosEntity fkVehiculos;

    @Column(name = "nombre_reserva", nullable = false, unique = true)
    private String nombreReserva;
    @Column(name = "fecha_reserva", nullable = false, unique = true)
    private LocalDate fechaReserva;
    @Column(name = "cantidad_pasajeros", nullable = false)
    private int cantidadPasajeros;
    @Column(name = "cantidad_dias", nullable = false)
    private int cantidadDias;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "total_pago", nullable = false)
    private BigDecimal totalPago;
}
