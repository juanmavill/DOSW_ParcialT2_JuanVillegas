package edu.dosw.parcial.entity;

import edu.dosw.parcial.model.PermissionName;
import jakarta.persistence.*;
@Entity
@Table(name = "permisos")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private PermissionName nombre;

    public Long getId() {
        return id;
    }

    public PermissionName getNombre() {
        return nombre;
    }

    public void setNombre(PermissionName nombre) {
        this.nombre = nombre;
    }
}