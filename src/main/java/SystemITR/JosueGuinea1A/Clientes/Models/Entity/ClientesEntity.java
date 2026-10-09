package SystemITR.JosueGuinea1A.Clientes.Models.Entity;

import SystemITR.JosueGuinea1A.Reservar.Models.Entity.ReservarEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "clientes")
public class ClientesEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    @Id
    private Long idCliente;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "fkCliente", fetch = FetchType.LAZY)
    private List<ReservarEntity> clientes;

}
