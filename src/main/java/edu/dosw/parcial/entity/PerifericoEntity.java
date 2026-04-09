package edu.dosw.parcial.entity;

import edu.dosw.parcial.model.PeripheralType;
import jakarta.persistence.*;


@Entity
@Table(name = "perifericos")
public class PerifericoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PeripheralType tipo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private boolean inalambrico;

    @ManyToOne
    @JoinColumn(name = "registrado_por_id", nullable = false)
    private ColaboradorEntity registradoPor;

    @ManyToOne
    @JoinColumn(name = "computador_id", nullable = false)
    private ComputadorEntity computador;

    public Long getId() {
        return id;
    }

    public PeripheralType getTipo() {
        return tipo;
    }

    public void setTipo(PeripheralType tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    public ColaboradorEntity getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(ColaboradorEntity registradoPor) {
        this.registradoPor = registradoPor;
    }

    public ComputadorEntity getComputador() {
        return computador;
    }

    public void setComputador(ComputadorEntity computador) {
        this.computador = computador;
    }
}