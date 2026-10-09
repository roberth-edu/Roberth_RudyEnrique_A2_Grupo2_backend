package SystemITR.JosueGuinea1A.Vehiculos.Models.Entity;

import SystemITR.JosueGuinea1A.Reservar.Models.Entity.ReservarEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "vehiculos")
public class VehiculosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    @Id
    private Long idVehiculo;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name = "modelo", nullable = false)
    private String modelo;
    @Column(name = "capacidad", nullable = false)
    private int Capacidad;
    @Column(name = "precio_dia", nullable = false)
    private BigDecimal precio_dia;
    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @OneToMany(mappedBy = "fkVehiculos", fetch = FetchType.LAZY)
    private List<ReservarEntity> vehiculos;
}
