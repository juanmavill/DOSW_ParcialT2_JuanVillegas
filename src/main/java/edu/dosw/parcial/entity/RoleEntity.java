package edu.dosw.parcial.entity;

import edu.dosw.parcial.model.RoleName;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 30)
    private RoleName nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permisos",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id"))
    private Set<PermissionEntity> permisos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public RoleName getNombre() {
        return nombre;
    }

    public void setNombre(RoleName nombre) {
        this.nombre = nombre;
    }

    public Set<PermissionEntity> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<PermissionEntity> permisos) {
        this.permisos = permisos;
    }
}