package edu.dosw.parcial.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "computadores")
public class ComputadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serial;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private Integer numeroLaboratorio;

    @Column(nullable = false)
    private boolean ocupado;

    @ManyToOne
    @JoinColumn(name = "registrado_por_id", nullable = false)
    private ColaboradorEntity registradoPor;

    @OneToMany(mappedBy = "computador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PerifericoEntity> perifericos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getNumeroLaboratorio() {
        return numeroLaboratorio;
    }

    public void setNumeroLaboratorio(Integer numeroLaboratorio) {
        this.numeroLaboratorio = numeroLaboratorio;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public ColaboradorEntity getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(ColaboradorEntity registradoPor) {
        this.registradoPor = registradoPor;
    }

    public List<PerifericoEntity> getPerifericos() {
        return perifericos;
    }

    public void setPerifericos(List<PerifericoEntity> perifericos) {
        this.perifericos = perifericos;
    }
}